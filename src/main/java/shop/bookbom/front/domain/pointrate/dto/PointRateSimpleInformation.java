package shop.bookbom.front.domain.pointrate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.pointrate.dto
 * fileName       : PointRateSimpleInformation
 * author         : UuLaptop
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class PointRateSimpleInformation {
    private String earnType;
    private Integer earnPoint;

    @Builder
    public PointRateSimpleInformation(String earnType, Integer earnPoint) {
        this.earnType = earnType;
        this.earnPoint = earnPoint;
    }
}
