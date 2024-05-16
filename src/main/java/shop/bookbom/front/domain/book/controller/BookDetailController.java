package shop.bookbom.front.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.service.BookService;
import shop.bookbom.front.domain.review.dto.response.BookReviewResponse;
import shop.bookbom.front.domain.review.service.ReviewService;

@Controller
@RequiredArgsConstructor
public class BookDetailController {
    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/books/detail/{id}")
    public String index(Model model,
                        @PathVariable("id") Long bookId,
                        @PageableDefault(size = 5) Pageable pageable
    ) {

        BookDetailResponse bookDetail = bookService.getBook(bookId);
        Page<BookReviewResponse> reviews = reviewService.getReviews(bookId, pageable);
        model.addAttribute("bookDetail", bookDetail);
        model.addAttribute("reviews", reviews);
        return "page/book/bookdetail";
    }
}
