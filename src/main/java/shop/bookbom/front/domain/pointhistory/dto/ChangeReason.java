package shop.bookbom.front.domain.pointhistory.dto;

import lombok.Getter;

@Getter
public enum ChangeReason {
    EARN("적립"),
    USE("사용"),
    ;

    private final String value;

    ChangeReason(String value) {
        this.value = value;
    }
}
