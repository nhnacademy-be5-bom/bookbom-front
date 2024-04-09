package shop.bookbom.front.domain.book.controller;

import feign.Headers;
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
import shop.bookbom.front.domain.category.service.CategoryService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AddBookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/addbook")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories().getCategories());
        return "page/book/addbook";
    }

    @PutMapping("/addbook")
    @Headers("Content-Type: multipart/form-data")
    public String putBook(@ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest,
                          Model model) {

        bookService.putBook(bookAddRequest);
        return "page/book/addbook";
    }

}
