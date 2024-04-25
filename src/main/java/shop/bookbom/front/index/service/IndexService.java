package shop.bookbom.front.index.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

public interface IndexService {
    /**
     * 최신 책 목록을 가져옵니다.
     *
     * @param pageable 가져올 책 갯수
     * @return 최신 책 목록
     */
    Page<BookSearchResponse> mainLatestBooks(Pageable pageable);

    /**
     * 베스트 책 목록을 가져옵니다.
     *
     * @param pageable 가져올 책 갯수
     * @return 베스트 책 목록
     */
    Page<BookSearchResponse> mainBestBooks(Pageable pageable);
}
