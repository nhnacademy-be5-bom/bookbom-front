package shop.bookbom.front.domain.admin.coupon.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.admin.coupon.adapter.AdminCouponAdapter;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponIssueResponse;

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

    @Override
    public <T> void createCoupon(String type, T addCouponDto, Long userId) {
        adminCouponAdapter.addCoupon(type, addCouponDto, userId);
    }

    @Override
    public Page<CouponInfoResponse> getCouponInfo(Pageable pageable, String type, Long userId) {
        return adminCouponAdapter.getConponInfo(pageable, type, userId);
    }

    @Override
    public void issueCoupon(IssueCouponRequest issueCouponRequest, Long userId) {
        adminCouponAdapter.issue(issueCouponRequest, userId);
    }

}
