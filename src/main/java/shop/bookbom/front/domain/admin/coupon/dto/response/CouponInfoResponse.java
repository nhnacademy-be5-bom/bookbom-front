package shop.bookbom.front.domain.admin.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.bookbom.front.domain.admin.coupon.entity.CouponType;
import shop.bookbom.front.domain.admin.coupon.entity.DiscountType;

@Getter
@RequiredArgsConstructor
public class CouponInfoResponse {
    private Long id;
    private String name;
    private CouponType type;

    private DiscountType discountType;
    private int discountCost;
    private int minOrderCost;
    private int maxDiscountCost;

    private String title;
    private String categoryName;

    @Builder
    private CouponInfoResponse(Long couponId, String name, CouponType couponType, DiscountType discountType,
                               int discountCost, int minOrderCost, int maxDiscountCost, String bookName,
                               String categoryName) {
        this.id = couponId;
        this.name = name;
        this.type = couponType;
        this.discountType = discountType;
        this.discountCost = discountCost;
        this.minOrderCost = minOrderCost;
        this.maxDiscountCost = maxDiscountCost;
        this.title = bookName;
        this.categoryName = categoryName;
    }
}
