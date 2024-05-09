package shop.bookbom.front.domain.admin.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CouponPolicyDeleteRequest {
    private Long couponPolicyId;

    @Builder
    private CouponPolicyDeleteRequest(Long couponPolicyId) {
        this.couponPolicyId = couponPolicyId;
    }

    public static CouponPolicyDeleteRequest of(Long couponPolicyId) {
        return CouponPolicyDeleteRequest.builder()
                .couponPolicyId(couponPolicyId)
                .build();
    }
}
