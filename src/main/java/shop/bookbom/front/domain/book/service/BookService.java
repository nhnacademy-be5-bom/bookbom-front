package shop.bookbom.front.domain.book.service;


import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.adapter.BookAdapter;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.dto.response.BookUpdateResponse;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdapter bookAdapter;

    public CommonResponse<Void> addBook(MultipartFile file, BookAddRequest bookAddRequest) throws IOException {
        return bookAdapter.save(file, bookAddRequest);
    }

    public BookDetailResponse getBook(Long bookId) {
        return bookAdapter.getBookDetail(bookId);
    }

    public BookUpdateResponse getBookUpdateInfo(Long bookId) {
        return bookAdapter.getBookUpdate(bookId);
    }

    public Page<BookSearchResponse> getBooksByCategoryId(Long categoryId,
                                                         Pageable pageable,
                                                         String sortCondition) {
        return bookAdapter.getByCategoryId(categoryId, pageable, sortCondition);
    }

    public CommonResponse<Void> updateBook(MultipartFile file, BookUpdateRequest bookUpdateRequest, Long bookId)
            throws IOException {
        return bookAdapter.update(file, bookUpdateRequest, bookId);
    }

    public Page<BookSearchResponse> getAllBooks(Pageable pageable, String searchCondition) {
        return bookAdapter.getAllBooks(pageable, searchCondition);
    }
}
