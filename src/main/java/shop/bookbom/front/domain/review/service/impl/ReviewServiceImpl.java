package shop.bookbom.front.domain.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.review.adapter.ReviewAdapter;
import shop.bookbom.front.domain.review.dto.ReviewCheckResponse;
import shop.bookbom.front.domain.review.dto.ReviewForm;
import shop.bookbom.front.domain.review.dto.response.BookReviewResponse;
import shop.bookbom.front.domain.review.service.ReviewService;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewAdapter reviewAdapter;

    @Override
    public void writeReview(ReviewForm reviewForm) {
        reviewAdapter.writeReview(reviewForm);
    }

    @Override
    public ReviewCheckResponse existsCheck(Long bookId, Long orderId) {
        return reviewAdapter.existsCheck(bookId, orderId);
    }

    @Override
    public Page<BookReviewResponse> getReviews(Long bookId, Pageable pageable) {
        return reviewAdapter.getReviews(bookId, pageable);
    }
}
