package shop.bookbom.front.domain.couponpolicy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CouponPolicyDto {
    private Long id;
    private int minOrderCost;
    private DiscountType discountType;
    private Integer discountCost;
    private Integer maxDiscountCost;

}
