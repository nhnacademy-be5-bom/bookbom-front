package shop.bookbom.front.domain.admin.coupon.adapter;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponIssueResponse;

public interface AdminCouponAdapter {
    void addCouponPolicy(CouponPolicyAddRequest request, Long userId);

    void deleteCouponPolicy(CouponPolicyDeleteRequest requests, Long userId);

    void updateCouponPolicy(CouponPolicyInfoDto request, Long userId);

    List<CouponPolicyInfoDto> getCouponPolicyInfo(Long userId);

    <T> void addCoupon(String type, T addCouponDto, Long userId);

    Page<CouponInfoResponse> getConponInfo(Pageable pageable, String type, Long userId);

    void issue(IssueCouponRequest issueCouponRequest, Long userId);
}
