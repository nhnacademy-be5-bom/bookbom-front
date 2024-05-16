package shop.bookbom.front.domain.admin.coupon.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.bookbom.front.domain.admin.coupon.adapter.AdminCouponAdapter;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.IssueCouponRequest;
import shop.bookbom.front.domain.admin.coupon.dto.response.CouponInfoResponse;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {
    private final AdminCouponAdapter adminCouponAdapter;

    @Override
    public void addCouponPolicy(CouponPolicyAddRequest request) {
        adminCouponAdapter.addCouponPolicy(request);
    }

    @Override
    public void deleteCouponPolicy(CouponPolicyDeleteRequest request) {
        adminCouponAdapter.deleteCouponPolicy(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponPolicyInfoDto> getCouponPolicyInfo() {
        return adminCouponAdapter.getCouponPolicyInfo();
    }

    @Override
    public void updateCouponPolicy(CouponPolicyInfoDto request) {
        adminCouponAdapter.updateCouponPolicy(request);
    }

    @Override
    public <T> void createCoupon(String type, T addCouponDto) {
        adminCouponAdapter.addCoupon(type, addCouponDto);
    }

    @Override
    public Page<CouponInfoResponse> getCouponInfo(Pageable pageable, String type) {
        return adminCouponAdapter.getConponInfo(pageable, type);
    }

    @Override
    public void issueCoupon(IssueCouponRequest issueCouponRequest) {
        adminCouponAdapter.issue(issueCouponRequest);
    }

}
