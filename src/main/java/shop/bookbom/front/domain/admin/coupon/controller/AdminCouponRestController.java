package shop.bookbom.front.domain.admin.coupon.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddBookCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddCategoryCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.AddCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.service.AdminCouponService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCouponRestController {
    private final AdminCouponService adminCouponService;

    @PostMapping("/couponPolicy")
    public CommonResponse<Void> addCouponPolicy(@RequestBody CouponPolicyAddRequest request) {
        adminCouponService.addCouponPolicy(request);
        return CommonResponse.success();
    }

    @DeleteMapping("/couponPolicy")
    public CommonResponse<Void> deleteCouponPolicy(@RequestBody CouponPolicyDeleteRequest request) {
        adminCouponService.deleteCouponPolicy(request);
        return CommonResponse.success();
    }

    @PutMapping("/couponPolicy")
    public CommonResponse<Void> updateCouponPolicy(@RequestBody CouponPolicyInfoDto request) {
        adminCouponService.updateCouponPolicy(request);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/general")
    public CommonResponse<Void> createCoupon(@RequestBody AddCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("general", addCouponRequest);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/book")
    public CommonResponse<Void> createCoupon(@RequestBody AddBookCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("book", addCouponRequest);
        return CommonResponse.success();
    }

    @PostMapping("/couponRegister/category")
    public CommonResponse<Void> createCoupon(@RequestBody AddCategoryCouponRequest addCouponRequest) {
        adminCouponService.createCoupon("category", addCouponRequest);
        return CommonResponse.success();
    }

    @PostMapping("/coupons/issue")
    public CommonResponse<Void> issueCoupon(@RequestBody @Valid IssueCouponRequest issueCouponRequest){
        adminCouponService.issueCoupon(issueCouponRequest);
        return CommonResponse.success();
    }
}
