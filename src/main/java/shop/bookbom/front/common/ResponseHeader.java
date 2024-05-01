package shop.bookbom.front.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
public class ResponseHeader {
    private boolean isSuccessful;
    @Getter
    private int resultCode;
    @Getter
    private String resultMessage;

    @Builder
    public ResponseHeader(boolean isSuccessful, int resultCode, String resultMessage) {
        this.isSuccessful = isSuccessful;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }
}
