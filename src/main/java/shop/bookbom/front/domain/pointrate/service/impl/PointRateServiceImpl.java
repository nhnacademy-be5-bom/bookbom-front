package shop.bookbom.front.domain.pointrate.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.pointrate.adapter.PointRateAdapter;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.dto.request.PointRateUpdateRequest;
import shop.bookbom.front.domain.pointrate.service.PointRateService;

@Service
@RequiredArgsConstructor
public class PointRateServiceImpl implements PointRateService {
    private final PointRateAdapter pointRateAdapter;


    public List<PointRate> getPointPolicies() {
        return pointRateAdapter.getPointPolicies();
    }

    @Override
    public PointRate updatePolicy(Long id, PointRateUpdateRequest request) {
        return pointRateAdapter.updatePolicy(id, request);
    }
}
