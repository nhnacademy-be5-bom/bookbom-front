package shop.bookbom.front.domain.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.review.adapter.ReviewAdapter;
import shop.bookbom.front.domain.review.dto.ReviewForm;
import shop.bookbom.front.domain.review.service.ReviewService;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewAdapter reviewAdapter;

    @Override
    public void writeReview(ReviewForm reviewForm) {
        reviewAdapter.writeReview(reviewForm);
    }
}
