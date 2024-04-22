package shop.bookbom.front.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.search.adaptor.SearchAdapter;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchAdapter searchAdapter;

    @Override
    public Page<BookSearchResponse> searchBook(String keyword, String searchCond, String sortCond, Pageable pageable) {
        return searchAdapter.searchBook(keyword, searchCond, sortCond, pageable);
    }
}
