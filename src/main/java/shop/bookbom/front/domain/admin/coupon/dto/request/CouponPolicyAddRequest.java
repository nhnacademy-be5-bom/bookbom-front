package shop.bookbom.front.domain.admin.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import shop.bookbom.front.domain.admin.coupon.entity.DiscountType;

@Getter
public class CouponPolicyAddRequest {
    private DiscountType discountType;
    private int discountCost;
    private int minOrderCost;
    private int maxDiscountCost;

    @Builder
    private CouponPolicyAddRequest(DiscountType discountType, int discountCost, int minOrderCost,
                                   int maxDiscountCost) {
        this.discountType = discountType;
        this.discountCost = discountCost;
        this.minOrderCost = minOrderCost;
        this.maxDiscountCost = maxDiscountCost;
    }

    public CouponPolicyAddRequest of(DiscountType discountType, int discountCost,
                                     int minOrderCost, int maxDiscountCost) {
        return CouponPolicyAddRequest.builder()
                .discountType(discountType)
                .discountCost(discountCost)
                .minOrderCost(minOrderCost)
                .maxDiscountCost(maxDiscountCost)
                .build();
    }
}
