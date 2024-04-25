package shop.bookbom.front.domain.book.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adaptor.BookAdaptor;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdaptor bookAdaptor;

    public CommonResponse<Void> addBook(BookAddRequest bookAddRequest) {
        return bookAdaptor.save(bookAddRequest);
    }

    public CommonResponse<BookDetailResponse> getBook(Long bookId) {
        return bookAdaptor.get(bookId);
    }

    public CommonResponse<Page<BookSearchResponse>> getBooksByCategoryId(Long categoryId,
                                                                         String sortCondition,
                                                                         Pageable pageable) {
        return bookAdaptor.getByCategoryId(categoryId, sortCondition, pageable);
    }

}
