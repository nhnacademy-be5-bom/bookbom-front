package shop.bookbom.front.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.review.dto.ReviewCheckResponse;
import shop.bookbom.front.domain.review.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    @GetMapping("/exists-check")
    public CommonResponse<ReviewCheckResponse> existsCheck(
            @RequestParam("bookId") Long bookId,
            @RequestParam("orderId") Long orderId
    ) {
        ReviewCheckResponse response = reviewService.existsCheck(bookId, orderId);
        return CommonResponse.successWithData(response);
    }
}
