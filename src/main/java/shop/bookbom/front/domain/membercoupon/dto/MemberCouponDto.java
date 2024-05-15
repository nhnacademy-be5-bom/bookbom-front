package shop.bookbom.front.domain.membercoupon.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.coupon.dto.CouponDto;
import shop.bookbom.front.domain.coupon.entity.CouponStatus;

@Getter
@NoArgsConstructor
public class MemberCouponDto {
    private Long id;
    private CouponStatus status;
    private LocalDate issueDate;
    private LocalDate expireDate;
    private LocalDate useDate;
    private Long couponId;
    private CouponDto couponDto;

}
