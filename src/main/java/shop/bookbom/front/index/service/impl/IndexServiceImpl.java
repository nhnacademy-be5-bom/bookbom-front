package shop.bookbom.front.index.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.index.adapter.IndexAdapter;
import shop.bookbom.front.index.service.IndexService;

@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final IndexAdapter indexAdapter;

    @Override
    public Page<BookSearchResponse> mainLatestBooks(Pageable pageable) {
        return indexAdapter.mainLatestBooks(pageable);
    }

    @Override
    public Page<BookSearchResponse> mainBestBooks(Pageable pageable) {
        return indexAdapter.mainBestBooks(pageable);
    }
}
