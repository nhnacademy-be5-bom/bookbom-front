package shop.bookbom.front.domain.admin.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddBookCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddCategoryCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.service.AdminCouponService;

@RestController
@RequiredArgsConstructor
public class AdminCouponRestController {
    private final AdminCouponService adminCouponService;

    Long userId = 1L; //테스트

    @PostMapping("/couponPolicy")
    public CommonResponse<Void> addCouponPolicy(@RequestBody CouponPolicyAddRequest request) {
        adminCouponService.addCouponPolicy(request, userId);
        return CommonResponse.success();
    }

    @DeleteMapping("/couponPolicy")
    public CommonResponse<Void> deleteCouponPolicy(@RequestBody CouponPolicyDeleteRequest request) {
        adminCouponService.deleteCouponPolicy(request, userId);
        return CommonResponse.success();
    }

    @PutMapping("/couponPolicy")
    public CommonResponse<Void> updateCouponPolicy(@RequestBody CouponPolicyInfoDto request) {
        adminCouponService.updateCouponPolicy(request, userId);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/general")
    public CommonResponse<Void> createCoupon(@RequestBody AddCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("general", addCouponRequest, userId);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/book")
    public CommonResponse<Void> createCoupon(@RequestBody AddBookCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("book", addCouponRequest, userId);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/category")
    public CommonResponse<Void> createCoupon(@RequestBody AddCategoryCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("category", addCouponRequest, userId);
        return CommonResponse.success();
    }
}
