package shop.bookbom.front.domain.admin.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCouponRequest {
    private String name;
    private Long couponPolicyId;

    @Builder
    private AddCouponRequest(String name, Long couponPolicyId) {
        this.name = name;
        this.couponPolicyId = couponPolicyId;
    }

    public AddCouponRequest of(String name, Long couponPolicyId) {
        return AddCouponRequest.builder()
                .name(name)
                .couponPolicyId(couponPolicyId)
                .build();
    }
}
