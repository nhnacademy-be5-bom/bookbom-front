package shop.bookbom.front.domain.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adaptor.BookAdaptor;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdaptor bookAdaptor;
    private final ObjectMapper mapper;

    public CommonResponse<Void> putBook(BookAddRequest bookAddRequest) {
        return bookAdaptor.save(bookAddRequest);
    }
}
