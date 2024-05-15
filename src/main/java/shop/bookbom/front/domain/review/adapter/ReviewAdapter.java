package shop.bookbom.front.domain.review.adapter;

import shop.bookbom.front.domain.review.dto.ReviewCheckResponse;
import shop.bookbom.front.domain.review.dto.ReviewForm;

public interface ReviewAdapter {
    /**
     * 리뷰 작성받은 폼을 API 서버로 전송하는 메서드입니다.
     *
     * @param reviewForm 리뷰 폼
     */
    void writeReview(ReviewForm reviewForm);

    /**
     * 해당 도서에 대해 이미 작성한 리뷰가 있는지 체크하는 메서드입니다.
     * @param bookId 도서 id
     * @param orderId 주문 id
     * @return ReviewCheckResponse(리뷰가 존재하는지 여부)
     */
    ReviewCheckResponse existsCheck(Long bookId, Long orderId);
}
