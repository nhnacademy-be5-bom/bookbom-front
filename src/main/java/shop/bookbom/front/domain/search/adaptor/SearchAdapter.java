package shop.bookbom.front.domain.search.adaptor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

public interface SearchAdapter {
    Page<BookSearchResponse> searchBook(String keyword, String searchCond, String sortCond, Pageable pageable);
}
