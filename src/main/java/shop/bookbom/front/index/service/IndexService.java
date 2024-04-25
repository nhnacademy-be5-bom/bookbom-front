package shop.bookbom.front.index.service;

import java.util.List;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

public interface IndexService {
    /**
     * 메인 페이지 최신 책 목록을 가져옵니다.
     *
     * @param size 가져올 책 갯수
     * @return 최신 책 목록
     */
    List<BookSearchResponse> mainLatestBooks(int size);

    /**
     * 메인 페이지 베스트 책 목록을 가져옵니다.
     *
     * @param size 가져올 책 갯수
     * @return 베스트 책 목록
     */
    List<BookSearchResponse> mainBestBooks(int size);
}
