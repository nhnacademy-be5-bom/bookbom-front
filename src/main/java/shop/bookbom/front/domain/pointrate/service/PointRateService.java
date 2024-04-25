package shop.bookbom.front.domain.pointrate.service;

import java.util.List;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.dto.request.PointRateUpdateRequest;

public interface PointRateService {
    List<PointRate> getPointPolicies();
    PointRate updatePolicy(Long id, PointRateUpdateRequest request);
}
