package shop.bookbom.front.domain.admin.coupon.dto.request;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCouponRequest {
    private String name;
    private Long couponPolicyId;
    private Date period;

    @Builder
    private AddCouponRequest(String name, Long couponPolicyId, Date period) {
        this.name = name;
        this.couponPolicyId = couponPolicyId;
        this.period = period;
    }

    public AddCouponRequest of(String name, Long couponPolicyId, Date period) {
        return AddCouponRequest.builder()
                .name(name)
                .couponPolicyId(couponPolicyId)
                .period(period)
                .build();
    }
}
