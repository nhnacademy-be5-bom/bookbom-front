package shop.bookbom.front.domain.review.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @GetMapping
    public String review(
            @RequestParam("bookId") Long bookId,
            @RequestParam("orderId") Long orderId,
            @RequestParam("reviewType") String type
    ) {
        log.info("review bookId: {}, orderId: {}, type: {}", bookId, orderId, type);
        return "page/review/write-review";
    }
}
