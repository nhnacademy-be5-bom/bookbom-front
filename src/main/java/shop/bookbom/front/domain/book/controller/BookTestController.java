package shop.bookbom.front.domain.book.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookTestController {
    @GetMapping("/booktest")
    public String index(HttpServletRequest request, Model model) {
        return "page/book/addbook";
    }
}
