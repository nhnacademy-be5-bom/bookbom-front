package shop.bookbom.front.domain.admin.coupon.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;
import shop.bookbom.front.domain.admin.coupon.service.AdminCouponService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCouponController {
    private final AdminCouponService adminCouponService;

    Long userId = 1L; //테스트

    @GetMapping("/coupons/policy")
    public String getCouponPolicyList(Model model) {
        List<CouponPolicyInfoDto> couponPolicyList = adminCouponService.getCouponPolicyInfo(userId);
        model.addAttribute("couponPolicyList", couponPolicyList);
        return "page/admin/coupon/coupon_policy";
    }

    @GetMapping("/coupons/register")
    public String getCouponRegisterPage(Model model) {
        return "page/admin/coupon/coupon_registration";
    }

    @GetMapping("/coupons/{type}")
    public String getCouponList(Model model, @PathVariable("type") String type, @PageableDefault Pageable pageable) {
        Page<CouponInfoResponse> couponInfoList = adminCouponService.getCouponInfo(pageable, type, userId);
        model.addAttribute("selectType", type);
        model.addAttribute("couponInfoList", couponInfoList);
        return "page/admin/coupon/coupon_admin";
    }

    @GetMapping("/coupons/issue")
    public String getCouponIssuePage(Model model) {
        return "page/admin/coupon/coupon_issuance";
    }
}
