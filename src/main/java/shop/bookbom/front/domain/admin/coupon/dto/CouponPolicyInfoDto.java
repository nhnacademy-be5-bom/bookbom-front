package shop.bookbom.front.domain.admin.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.admin.coupon.entity.DiscountType;


@Getter
@NoArgsConstructor
public class CouponPolicyInfoDto {
    private Long couponPolicyId;
    private DiscountType discountType;
    private int discountCost;
    private int minOrderCost;
    private int maxDiscountCost;

    @Builder
    private CouponPolicyInfoDto(Long couponPolicyId, DiscountType discountType, int discountCost,
                                int minOrderCost, int maxDiscountCost) {
        this.couponPolicyId = couponPolicyId;
        this.discountType = discountType;
        this.discountCost = discountCost;
        this.minOrderCost = minOrderCost;
        this.maxDiscountCost = maxDiscountCost;
    }

    public static CouponPolicyInfoDto of(Long couponPolicyId, DiscountType discountType, int discountCost,
                                         int minOrderCost, int maxDiscountCost) {
        return CouponPolicyInfoDto.builder()
                .couponPolicyId(couponPolicyId)
                .discountType(discountType)
                .discountCost(discountCost)
                .minOrderCost(minOrderCost)
                .maxDiscountCost(maxDiscountCost)
                .build();
    }
}
