package shop.bookbom.front.domain.pointrate.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRate {

    private Long id;
    private String name;
    private EarnPointType earnType;
    private int earnPoint;
    private ApplyPointType applyType;
    private LocalDateTime createdAt;

    public PointRate(
            String name,
            EarnPointType earnType,
            int earnPoint,
            ApplyPointType applyType,
            LocalDateTime createdAt
    ) {
        this.name = name;
        this.earnType = earnType;
        this.earnPoint = earnPoint;
        this.applyType = applyType;
        this.createdAt = createdAt;
    }
}
