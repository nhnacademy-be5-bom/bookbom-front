package shop.bookbom.front.domain.review.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.review.dto.ReviewForm;
import shop.bookbom.front.domain.review.service.ReviewService;

@Slf4j
@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public String review(
            @RequestParam("bookId") Long bookId,
            @RequestParam("orderId") Long orderId,
            @RequestParam("reviewType") String type,
            @ModelAttribute("reviewForm") ReviewForm reviewForm,
            Model model
    ) {
        model.addAttribute("bookId", bookId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("type", type);
        return "page/review/write-review";
    }

    @PostMapping
    public String writeReview(@ModelAttribute @Valid ReviewForm reviewForm, BindingResult bindingResult) {
        if (reviewForm.getRating() == 0) {
            bindingResult.reject("error.rating", "별점을 선택해주세요.");
        }
        if (reviewForm.getType().equals("photo") && reviewForm.getImage() == null) {
            bindingResult.reject("error.image", "첨부파일을 입력해주세요.");
        }
        reviewService.writeReview(reviewForm);
        return "redirect:/books/detail/" + reviewForm.getBookId();
    }
}
