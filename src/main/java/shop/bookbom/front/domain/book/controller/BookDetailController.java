package shop.bookbom.front.domain.book.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookDetailController {
    private final BookService bookService;

    @GetMapping("/book/detail/{id}")
    public String index(HttpServletRequest request,
                        Model model,
                        @PathVariable("id") Long bookId) {

        BookDetailResponse bookDetail = bookService.getBook(bookId);
        model.addAttribute("bookDetail", bookDetail);
        return "page/book/bookdetail";
    }

}
