package shop.bookbom.front.domain.pointrate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointRate {
    private Long id;
    private String name;
    private EarnPointType earnType;
    private int earnPoint;
}
