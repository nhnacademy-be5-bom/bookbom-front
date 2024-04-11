package shop.bookbom.front.domain.book.controller;

import feign.Headers;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.service.BookService;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.service.CategoryService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AddBookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/addbook")
    public String addBookPage(HttpServletRequest request, Model model) {
        List<CategoryDTO> list_depth1 = categoryService.getDepthOneCategories();

        model.addAttribute("categories_depth1", list_depth1);

        return "page/book/addbook";
    }

    @PutMapping("/addbook")
    @Headers("Content-Type: multipart/form-data")
    public String putBook(@ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest,
                          Model model) {

        bookService.putBook(bookAddRequest);
        //model.addAttribute("success", true);

        return "page/book/addbook";
    }

}
