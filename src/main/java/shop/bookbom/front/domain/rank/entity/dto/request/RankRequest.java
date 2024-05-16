package shop.bookbom.front.domain.rank.entity.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.pointrate.dto.EarnPointType;

@Getter
@NoArgsConstructor
public class RankRequest {
    private String name;
    private int earnPoint;
    private EarnPointType earnType;
}
