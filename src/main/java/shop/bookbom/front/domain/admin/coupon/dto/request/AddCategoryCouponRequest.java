package shop.bookbom.front.domain.admin.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCategoryCouponRequest {
    private String name;
    private Long couponPolicyId;
    private Long categoryId;

    @Builder
    private AddCategoryCouponRequest(String name, Long couponPolicyId, Long categoryId) {
        this.name = name;
        this.couponPolicyId = couponPolicyId;
        this.categoryId = categoryId;
    }

    public AddCategoryCouponRequest of(String name, Long couponPolicyId, Long categoryId) {
        return AddCategoryCouponRequest.builder()
                .name(name)
                .couponPolicyId(couponPolicyId)
                .categoryId(categoryId)
                .build();
    }
}
