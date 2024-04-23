package shop.bookbom.front.domain.pointrate.adapter;

import java.util.List;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.dto.request.PointRateUpdateRequest;

public interface PointRateAdapter {
    List<PointRate> getPointPolicies();

    PointRate updatePolicy(Long id, PointRateUpdateRequest request);
}
