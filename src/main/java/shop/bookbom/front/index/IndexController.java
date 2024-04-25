package shop.bookbom.front.index;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.index.service.IndexService;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private static final int MAIN_BOOK_SIZE = 4;
    private final IndexService indexService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        model.addAttribute("ip", ip);

        List<BookSearchResponse> latestBooks = indexService.mainLatestBooks(MAIN_BOOK_SIZE);
        List<BookSearchResponse> bestBooks = indexService.mainBestBooks(MAIN_BOOK_SIZE);
        model.addAttribute("bestBooks", bestBooks);
        model.addAttribute("latestBooks", latestBooks);
        return "page/main";
    }

    @GetMapping("/selectWrapper")
    public String showSelectWrpperPage() {
        return "page/order/selectWrapper";
    }

    @GetMapping("/ordersheet")
    public String showOrderPage() {
        return "page/order/ordersheet_member";
    }

    @GetMapping("/ordersheet_non_member")
    public String showOrderPage_non_member() {
        return "page/order/ordersheet_non_member";
    }

    @GetMapping("/myCoupon")
    public String showMyCouponPage() {
        return "page/coupon/myCoupon";
    }

    @GetMapping("/myCouponDetail")
    public String showMyCouponDetailPage() {
        return "page/coupon/myCoupon_detail";
    }

    @GetMapping("/tosspay")
    public String showtosspay() {
        return "page/payment/tosspay";
    }

    @GetMapping("/toss/success")
    public String showtosspay_success() {
        return "page/payment/tosssuccess";
    }


}
