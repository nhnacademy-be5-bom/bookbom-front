package shop.bookbom.front.domain.pointrate.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.pointrate.dto.EarnPointType;

@Getter
@NoArgsConstructor
public class PointRateUpdateRequest {
    private EarnPointType earnType;
    private int earnPoint;
}
