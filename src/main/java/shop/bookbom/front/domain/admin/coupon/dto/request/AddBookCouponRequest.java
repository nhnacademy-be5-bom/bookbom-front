package shop.bookbom.front.domain.admin.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddBookCouponRequest {
    private String name;
    private Long couponPolicyId;
    private Long bookId;

    @Builder
    private AddBookCouponRequest(String name, Long couponPolicyId, Long bookId) {
        this.name = name;
        this.couponPolicyId = couponPolicyId;
        this.bookId = bookId;
    }

    public AddBookCouponRequest of(String name, Long couponPolicyId, Long bookId) {
        return AddBookCouponRequest.builder()
                .name(name)
                .couponPolicyId(couponPolicyId)
                .bookId(bookId)
                .build();
    }
}
