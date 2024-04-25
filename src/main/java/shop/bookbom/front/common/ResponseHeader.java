package shop.bookbom.front.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseHeader {
    private boolean isSuccessful;
    private int resultCode;
    private String resultMessage;
}
