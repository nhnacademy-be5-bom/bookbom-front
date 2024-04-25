package shop.bookbom.front.domain.book.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adaptor.BookAdaptor;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;

/**
 * The type Book service.
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdaptor bookAdaptor;

    /**
     * methodName : putBook
     * author : 전석준
     * description :
     *
     * @param book add request
     * @return common response
     */
    public CommonResponse<Void> putBook(BookAddRequest bookAddRequest) {
        return bookAdaptor.save(bookAddRequest);
    }

    /**
     * methodName : getBook
     * author : 전석준
     * description :
     *
     * @param book id
     * @return common response
     */
    public CommonResponse<BookDetailResponse> getBook(Long bookId) {
        return bookAdaptor.get(bookId);
    }
}
