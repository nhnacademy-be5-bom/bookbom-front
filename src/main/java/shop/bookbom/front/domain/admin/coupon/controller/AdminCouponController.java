package shop.bookbom.front.domain.admin.coupon.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.service.AdminCouponService;

@Controller
@RequiredArgsConstructor
public class AdminCouponController {
    private final AdminCouponService adminCouponService;

    Long userId = 1L; //테스트

    @GetMapping("/couponPolicy")
    public String getCouponPolicyList(Model model) {
        List<CouponPolicyInfoDto> couponPolicyList = adminCouponService.getCouponPolicyInfo(userId);
        model.addAttribute("couponPolicyList", couponPolicyList);
        return "page/admin/coupon/coupon_policy";
    }

    @GetMapping("/couponRegister")
    public String getCouponRegisterPage(Model model) {
        return "page/admin/coupon/coupon_registration";
    }
}
