package shop.bookbom.front.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.review.dto.ReviewCheckResponse;
import shop.bookbom.front.domain.review.dto.ReviewForm;
import shop.bookbom.front.domain.review.dto.response.BookReviewResponse;

public interface ReviewService {
    /**
     * 리뷰 작성
     *
     * @param reviewForm 리뷰 폼
     */
    void writeReview(ReviewForm reviewForm);

    /**
     * 해당 도서에 대해 이미 작성한 리뷰가 있는지 체크하는 메서드입니다.
     *
     * @param bookId  도서 id
     * @param orderId 주문 id
     * @return ReviewCheckResponse(리뷰가 존재하는지 여부)
     */
    ReviewCheckResponse existsCheck(Long bookId, Long orderId);

    /**
     * 해당 도서에 대한 리뷰 목록을 조회하는 메서드입니다.
     *
     * @param bookId   도서 id
     * @param pageable 페이징 정보
     * @return Page<BookReviewResponse>(리뷰 목록)
     */
    Page<BookReviewResponse> getReviews(Long bookId, Pageable pageable);
}
