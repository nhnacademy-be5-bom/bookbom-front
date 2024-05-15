package shop.bookbom.front.domain.coupon.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponRecordResponse;

public interface MemberCouponAdapter {
    Page<MyCouponInfoResponse> getMyConponInfo(Pageable pageable);

    Page<MyCouponRecordResponse> getMyCouponRecord(Pageable pageable);
}
