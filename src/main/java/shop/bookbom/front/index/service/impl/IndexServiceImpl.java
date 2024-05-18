package shop.bookbom.front.index.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.index.adapter.IndexAdapter;
import shop.bookbom.front.index.service.IndexService;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "main")
public class IndexServiceImpl implements IndexService {
    private final IndexAdapter indexAdapter;

    @Override
    @Cacheable(key = "'latestBooks'", condition = "#pageable.pageNumber == 0 && #pageable.pageSize == 8")
    public Page<BookSearchResponse> mainLatestBooks(Pageable pageable) {
        return indexAdapter.mainLatestBooks(pageable);
    }

    @Override
    @Cacheable(key = "'bestBooks'", condition = "#pageable.pageNumber == 0 && #pageable.pageSize == 8")
    public Page<BookSearchResponse> mainBestBooks(Pageable pageable) {
        return indexAdapter.mainBestBooks(pageable);
    }
}
