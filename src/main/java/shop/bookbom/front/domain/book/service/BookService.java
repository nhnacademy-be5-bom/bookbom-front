package shop.bookbom.front.domain.book.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adapter.BookAdapter;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdapter bookAdaptor;

    public CommonResponse<Void> addBook(BookAddRequest bookAddRequest) {
        return bookAdaptor.save(bookAddRequest);
    }

    public CommonResponse<BookDetailResponse> getBook(Long bookId) {
        return bookAdaptor.get(bookId);
    }

    public CommonResponse<CommonPage<BookSearchResponse>> getBooksByCategoryId(Long categoryId,
                                                                               Pageable pageable,
                                                                               String sortCondition) {
        return bookAdaptor.getByCategoryId(categoryId, pageable, sortCondition);
    }

}
