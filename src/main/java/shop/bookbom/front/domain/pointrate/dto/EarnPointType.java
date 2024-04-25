package shop.bookbom.front.domain.pointrate.dto;

import lombok.Getter;

@Getter
public enum EarnPointType {
    COST("금액"),
    RATE("비율");

    private String value;

    EarnPointType(String value) {
        this.value = value;
    }
}
