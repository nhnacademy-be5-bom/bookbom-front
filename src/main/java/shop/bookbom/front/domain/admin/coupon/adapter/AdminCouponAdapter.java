package shop.bookbom.front.domain.admin.coupon.adapter;

import java.util.List;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;

public interface AdminCouponAdapter {
    void addCouponPolicy(CouponPolicyAddRequest request, Long userId);

    void deleteCouponPolicy(CouponPolicyDeleteRequest requests, Long userId);

    void updateCouponPolicy(CouponPolicyInfoDto request, Long userId);

    List<CouponPolicyInfoDto> getCouponPolicyInfo(Long userId);
}
