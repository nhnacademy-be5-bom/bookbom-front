package shop.bookbom.front.index.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.book.dto.response.BookMediumResponse;

public interface IndexAdapter {
    /**
     * 최신 책 목록을 가져옵니다.
     *
     * @param pageable 페이지 정보
     * @return 최신 책 목록
     */
    Page<BookMediumResponse> mainLatestBooks(Pageable pageable);

    /**
     * 인기 책 목록을 가져옵니다.
     *
     * @param pageable 페이지 정보
     * @return 최신 책 목록
     */
    Page<BookMediumResponse> mainBestBooks(Pageable pageable);
}
