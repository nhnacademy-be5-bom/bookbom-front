package shop.bookbom.front.domain.admin.coupon.adapter;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;

public interface AdminCouponAdapter {
    void addCouponPolicy(CouponPolicyAddRequest request);

    void deleteCouponPolicy(CouponPolicyDeleteRequest requests);

    void updateCouponPolicy(CouponPolicyInfoDto request);

    List<CouponPolicyInfoDto> getCouponPolicyInfo();

    <T> void addCoupon(String type, T addCouponDto);

    Page<CouponInfoResponse> getConponInfo(Pageable pageable, String type);

    void issue(IssueCouponRequest issueCouponRequest);
}
