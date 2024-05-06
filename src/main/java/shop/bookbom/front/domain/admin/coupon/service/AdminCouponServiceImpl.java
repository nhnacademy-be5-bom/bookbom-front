package shop.bookbom.front.domain.admin.coupon.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.admin.coupon.adapter.AdminCouponAdapter;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {
    private final AdminCouponAdapter adminCouponAdapter;

    @Override
    public void addCouponPolicy(CouponPolicyAddRequest request, Long userId) {
        adminCouponAdapter.addCouponPolicy(request, userId);
    }

    @Override
    public void deleteCouponPolicy(CouponPolicyDeleteRequest request, Long userId) {
        adminCouponAdapter.deleteCouponPolicy(request, userId);
    }

    @Override
    public List<CouponPolicyInfoDto> getCouponPolicyInfo(Long userId) {
        return adminCouponAdapter.getCouponPolicyInfo(userId);
    }

    @Override
    public void updateCouponPolicy(CouponPolicyInfoDto request, Long userId) {
        adminCouponAdapter.updateCouponPolicy(request, userId);
    }
}
