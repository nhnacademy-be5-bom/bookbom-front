package shop.bookbom.front.domain.book.controller;

import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class AddBookController {
    private final BookService bookService;

    @PutMapping("book/update")
    @Headers("Content-Type: multipart/form-data")
    public CommonResponse<Void> putBook(@ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest) {
        return bookService.putBook(bookAddRequest);
    }

}
