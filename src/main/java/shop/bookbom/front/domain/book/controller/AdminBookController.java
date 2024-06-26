package shop.bookbom.front.domain.book.controller;

import feign.Headers;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.dto.response.BookUpdateResponse;
import shop.bookbom.front.domain.book.service.BookService;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.service.CategoryService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminBookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/addbook")
    public String addBookPage(HttpServletRequest request, Model model) {
        List<CategoryDTO> list_depth1 = categoryService.getDepthOneCategories();
        model.addAttribute("categories_depth1", list_depth1);
        model.addAttribute("bookAddRequest", new BookAddRequest());

        return "page/book/addbook";
    }

    @PostMapping(value = "/addbook", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Headers("Content-Type: multipart/form-data")
    public String addBook(@RequestPart("thumbnail") MultipartFile thumbnail,
                          @ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest,
                          RedirectAttributes redirectAttributes) throws IOException {

        CommonResponse<Void> response = bookService.addBook(thumbnail, bookAddRequest);

        redirectAttributes.addFlashAttribute("success", response.getHeader().isSuccessful());
        redirectAttributes.addFlashAttribute("message", response.getHeader().getResultMessage());

        return "redirect:/admin/addbook/result";
    }

    @GetMapping("/addbook/result")
    public String addBookResultPage() {
        return "page/book/result/addbook-result";
    }

    @GetMapping("/updatebook")
    public String bookListPage(@PageableDefault(size = 15) Pageable pageable,
                               @RequestParam(required = false) String keyword,
                               Model model) {

        String searchCondition = "NONE";

        if (StringUtils.hasText(keyword)) {
            searchCondition = keyword;
        }

        Page<BookSearchResponse> bookResponse = bookService.getAllBooks(pageable, searchCondition);

        model.addAttribute("books", bookResponse.getContent());
        model.addAttribute("currentPage", bookResponse.getNumber());
        model.addAttribute("pageSize", bookResponse.getPageable().getPageSize());
        model.addAttribute("totalPages", bookResponse.getTotalPages());
        model.addAttribute("totalItems", bookResponse.getTotalElements());
        model.addAttribute("size", bookResponse.getSize());

        return "page/admin/book-list";
    }

    @GetMapping("/updatebook/{bookId}")
    public String updateBookPage(@PathVariable("bookId") Long bookId,
                                 Model model) {
        List<CategoryDTO> list_depth1 = categoryService.getDepthOneCategories();
        model.addAttribute("categories_depth1", list_depth1);

        BookUpdateResponse bookUpdateResponse = bookService.getBookUpdateInfo(bookId);
        model.addAttribute("bookUpdateInfo", bookUpdateResponse);

        return "page/book/updatebook";
    }

    @PostMapping(value = "/updatebook/{bookId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Headers("Content-Type: multipart/form-data")
    public String updateBook(@RequestPart("thumbnail") MultipartFile thumbnail,
                             @PathVariable("bookId") Long bookId,
                             @ModelAttribute("bookAddRequest") BookUpdateRequest bookUpdateRequest,
                             RedirectAttributes redirectAttributes) throws IOException {

        CommonResponse<Void> response = bookService.updateBook(thumbnail, bookUpdateRequest, bookId);

        redirectAttributes.addFlashAttribute("success", response.getHeader().isSuccessful());
        redirectAttributes.addFlashAttribute("message", response.getHeader().getResultMessage());

        return "redirect:/admin/updatebook/result";
    }

    @GetMapping("/updatebook/result")
    public String updateBookResultPage() {
        return "page/book/result/updatebook-result";
    }
}
