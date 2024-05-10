package shop.bookbom.front.domain.admin.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.bookbom.front.domain.admin.coupon.entity.CouponType;

@Getter
@RequiredArgsConstructor
public class CouponIssueResponse {
    private Long couponId;
    private String name;
    private CouponType type;

    @Builder
    private CouponIssueResponse(Long couponId, String name, CouponType type) {
        this.couponId = couponId;
        this.name = name;
        this.type = type;
    }
}
