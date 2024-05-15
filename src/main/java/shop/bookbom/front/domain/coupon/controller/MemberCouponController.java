package shop.bookbom.front.domain.coupon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponRecordResponse;
import shop.bookbom.front.domain.coupon.service.MemberCouponService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberCouponController {
    private final MemberCouponService memberCouponService;
    //Long userId = 1L; //test

    @GetMapping("/mycoupons")
    public String getMyCouponList(Model model, @PageableDefault Pageable pageable) {
        Page<MyCouponInfoResponse> mycouponList = memberCouponService.getMyCouponInfo(pageable);
        model.addAttribute("totalCouponCount", mycouponList.getTotalElements());
        model.addAttribute("mycoupons", mycouponList);
        return "page/coupon/myCoupon";
    }

    @GetMapping("/mycoupons/detail")
    public String getMyCouponDetail(Model model, @PageableDefault Pageable pageable) {
        Page<MyCouponRecordResponse> records = memberCouponService.getMyCouponRecode(pageable);
        model.addAttribute("records", records);
        return "page/coupon/myCoupon_detail";
    }
}
