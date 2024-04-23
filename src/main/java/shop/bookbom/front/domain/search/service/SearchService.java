package shop.bookbom.front.domain.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

public interface SearchService {
    Page<BookSearchResponse> searchBook(String keyword, String searchCond, String sortCond, Pageable pageable);
}
