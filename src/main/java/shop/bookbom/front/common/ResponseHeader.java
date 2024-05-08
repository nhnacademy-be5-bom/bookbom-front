package shop.bookbom.front.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseHeader {
    private boolean successful;
    private int resultCode;
    private String resultMessage;

    @Builder
    public ResponseHeader(boolean successful, int resultCode, String resultMessage) {
        this.successful = successful;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
