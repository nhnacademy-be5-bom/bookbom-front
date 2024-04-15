package shop.bookbom.front.config.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeystoreResponse {
    private Header header;
    private Body body;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header {
        private Integer resultCode;
        private String resultMessage;
        private boolean isSuccessful;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String secret;
    }
}
