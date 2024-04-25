package shop.bookbom.front.index.adapter;

import org.springframework.data.domain.Page;
import shop.bookbom.front.domain.book.dto.response.BookMediumResponse;

public interface IndexAdapter {
    /**
     * 메인 페이지 최신 책 목록을 가져옵니다.
     *
     * @param size 가져올 책 갯수
     * @return 최신 책 목록
     */
    Page<BookMediumResponse> mainLatestBooks(int size);

    /**
     * 메인 페이지 인기 책 목록을 가져옵니다.
     *
     * @param size 가져올 책 갯수
     * @return 최신 책 목록
     */
    Page<BookMediumResponse> mainBestBooks(int size);
}
