package shop.bookbom.front.index.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.index.service.IndexService;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private static final int MAIN_BEST_BOOK_SIZE = 8;
    private static final int MAIN_LATEST_BOOK_SIZE = 8;
    private final IndexService indexService;

    @GetMapping("/")
    public String mainPage(Model model) {
        Page<BookSearchResponse> latestBooks = indexService.mainLatestBooks(PageRequest.of(0, MAIN_LATEST_BOOK_SIZE));
        Page<BookSearchResponse> bestBooks = indexService.mainBestBooks(PageRequest.of(0, MAIN_BEST_BOOK_SIZE));
        model.addAttribute("bestBooks", bestBooks.getContent());
        model.addAttribute("latestBooks", latestBooks.getContent());
        return "page/main";
    }

    @GetMapping("/best-seller")
    public String showBestSellerPage(Model model, @PageableDefault Pageable pageable) {
        Page<BookSearchResponse> bestBooks = indexService.mainBestBooks(pageable);
        model.addAttribute("bestBooks", bestBooks.getContent());
        model.addAttribute("startNum", bestBooks.getNumber() * bestBooks.getSize());
        model.addAttribute("currentPage", bestBooks.getNumber());
        model.addAttribute("pageSize", bestBooks.getPageable().getPageSize());
        model.addAttribute("totalPages", bestBooks.getTotalPages());
        model.addAttribute("totalItems", bestBooks.getTotalElements());
        model.addAttribute("size", bestBooks.getSize());
        return "page/best-seller";
    }

    @GetMapping("/latest")
    public String showLatestPage(Model model, @PageableDefault(size = 9) Pageable pageable) {
        Page<BookSearchResponse> latestBooks = indexService.mainLatestBooks(pageable);
        model.addAttribute("latestBooks", latestBooks.getContent());
        model.addAttribute("currentPage", latestBooks.getNumber());
        model.addAttribute("pageSize", latestBooks.getPageable().getPageSize());
        model.addAttribute("totalPages", latestBooks.getTotalPages());
        model.addAttribute("totalItems", latestBooks.getTotalElements());
        model.addAttribute("size", latestBooks.getSize());
        return "page/latest-books";
    }

    @GetMapping("/myCoupon")
    public String showMyCouponPage() {
        return "page/coupon/myCoupon";
    }

    @GetMapping("/myCouponDetail")
    public String showMyCouponDetailPage() {
        return "page/coupon/myCoupon_detail";
    }

    @GetMapping("/admin")
    public String showAdminMainPage() {
        return "page/admin/admin_main";
    }
}
