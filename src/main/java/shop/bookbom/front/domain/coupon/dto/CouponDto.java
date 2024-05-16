package shop.bookbom.front.domain.coupon.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.couponbook.dto.CouponBookDto;
import shop.bookbom.front.domain.couponcategory.dto.CouponCategoryDto;
import shop.bookbom.front.domain.couponpolicy.dto.DiscountType;

@Getter
@NoArgsConstructor
public class CouponDto {
    private Long id;
    private String name;
    private CouponType type;
    //couponPolicy
    private int minOrderCost;
    private DiscountType discountType;
    private Integer discountCost;
    private Integer maxDiscountCost;

    private List<CouponBookDto> couponBooks;
    private List<CouponCategoryDto> couponCategories;

}
