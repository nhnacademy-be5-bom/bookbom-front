package shop.bookbom.front.domain.admin.coupon.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;

public interface AdminCouponService {
    void addCouponPolicy(CouponPolicyAddRequest request);

    void deleteCouponPolicy(CouponPolicyDeleteRequest request);

    void updateCouponPolicy(CouponPolicyInfoDto request);

    List<CouponPolicyInfoDto> getCouponPolicyInfo();

    <T> void createCoupon(String type, T addCouponDto);

    Page<CouponInfoResponse> getCouponInfo(Pageable pageable, String type);

    void issueCoupon(IssueCouponRequest issueCouponRequest);
}
