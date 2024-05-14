package shop.bookbom.front.domain.order.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.membercoupon.dto.MemberCouponDto;

@Getter
@NoArgsConstructor
public class WrapperSelectResponse {
    private int totalOrderCount;
    private List<WrapperSelectBookResponse> wrapperSelectResponseList;
    private List<String> estimatedDateList;
    private int wrapCost;
    private int point;
    private List<MemberCouponDto> availableMemberCoupons;
    private List<MemberCouponDto> unavailableMemberCoupons;
}
