package shop.bookbom.front.domain.book.controller;

import feign.Headers;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
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

    @PostMapping("/addbook")
    @Headers("Content-Type: multipart/form-data")
    public String addBook(@ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest,
                          Model model) {

        CommonResponse<Void> response = bookService.addBook(bookAddRequest);

        if (response.getHeader().getIsSuccessful()) {
            model.addAttribute("success", true);
            model.addAttribute("message", response.getHeader().getResultMessage());
        } else {
            model.addAttribute("success", false);
            model.addAttribute("message", response.getHeader().getResultMessage());
        }
        
        return "page/book/addbook";
    }
}
