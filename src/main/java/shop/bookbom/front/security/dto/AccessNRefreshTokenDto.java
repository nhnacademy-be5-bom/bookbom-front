package shop.bookbom.front.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccessNRefreshTokenDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public AccessNRefreshTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
