package shop.bookbom.front.domain.review.adapter;

import shop.bookbom.front.domain.review.dto.ReviewForm;

public interface ReviewAdapter {
    /**
     * 리뷰 작성받은 폼을 API 서버로 전송하는 메서드입니다.
     *
     * @param reviewForm 리뷰 폼
     */
    void writeReview(ReviewForm reviewForm);
}
