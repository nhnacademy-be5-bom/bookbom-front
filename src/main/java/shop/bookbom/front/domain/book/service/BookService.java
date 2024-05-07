package shop.bookbom.front.domain.book.service;


import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adapter.BookAdapter;
import shop.bookbom.front.domain.book.adapter.BookRestTemplateAdapter;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.dto.response.BookUpdateResponse;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdapter bookAdaptor;
    private final BookRestTemplateAdapter bookRestTemplateAdapter;

    public CommonResponse<Void> addBook(BookAddRequest bookAddRequest) {
        return bookAdaptor.save(bookAddRequest);
    }

    public CommonResponse<Void> addBook_REST(MultipartFile file, BookAddRequest bookAddRequest) throws IOException {
        return bookRestTemplateAdapter.save(file, bookAddRequest);
    }

    public CommonResponse<BookDetailResponse> getBook(Long bookId) {
        return bookAdaptor.get(bookId);
    }

    public CommonResponse<BookUpdateResponse> getBookUpdateInfo(Long bookId) {
        return bookAdaptor.getBookUpdate(bookId);
    }

    public CommonResponse<CommonPage<BookSearchResponse>> getBooksByCategoryId(Long categoryId,
                                                                               Pageable pageable,
                                                                               String sortCondition) {
        return bookAdaptor.getByCategoryId(categoryId, pageable, sortCondition);
    }

    public CommonResponse<Void> updateBook(MultipartFile file, BookUpdateRequest bookUpdateRequest, Long bookId)
            throws IOException {
        return bookRestTemplateAdapter.update(file, bookUpdateRequest, bookId);
    }

}
