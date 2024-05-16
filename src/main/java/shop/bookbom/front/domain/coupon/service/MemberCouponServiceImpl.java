package shop.bookbom.front.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.coupon.adapter.MemberCouponAdapter;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponRecordResponse;

@Service
@RequiredArgsConstructor
public class MemberCouponServiceImpl implements MemberCouponService{
    private final MemberCouponAdapter memberCouponAdapter;

    @Override
    public Page<MyCouponInfoResponse> getMyCouponInfo(Pageable pageable) {
        return memberCouponAdapter.getMyConponInfo(pageable);
    }

    @Override
    public Page<MyCouponRecordResponse> getMyCouponRecode(Pageable pageable) {
        return memberCouponAdapter.getMyCouponRecord(pageable);
    }
}
