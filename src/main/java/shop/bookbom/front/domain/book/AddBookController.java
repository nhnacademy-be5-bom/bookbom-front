package shop.bookbom.front.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adaptor.BookAdaptor;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class AddBookController {
    private final BookAdaptor bookAdaptor;

    @PutMapping("book/update")
    public CommonResponse<Void> putBook(@RequestParam BookAddRequest bookAddRequest) {
        return bookAdaptor.putBook(bookAddRequest);
    }

}
