package shop.bookbom.front.domain.review.service;

import shop.bookbom.front.domain.review.dto.ReviewForm;

public interface ReviewService {
    /**
     * 리뷰 작성
     *
     * @param reviewForm 리뷰 폼
     */
    void writeReview(ReviewForm reviewForm);
}
