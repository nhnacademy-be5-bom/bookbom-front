package shop.bookbom.front.domain.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponRecordResponse;

public interface MemberCouponService {
    Page<MyCouponInfoResponse> getMyCouponInfo(Pageable pageable);

    Page<MyCouponRecordResponse> getMyCouponRecode(Pageable pageable);
}
