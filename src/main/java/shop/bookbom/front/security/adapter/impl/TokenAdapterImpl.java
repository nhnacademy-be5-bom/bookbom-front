package shop.bookbom.front.security.adapter.impl;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.security.adapter.TokenAdapter;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;
import shop.bookbom.front.security.exception.TokenNotExistException;

@Component
@RequiredArgsConstructor
public class TokenAdapterImpl implements TokenAdapter {

    private static final ParameterizedTypeReference<CommonResponse<AccessNRefreshTokenDto>> USER_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public CommonResponse<AccessNRefreshTokenDto> refreshToken(String refreshToken) {

        URI uri = UriComponentsBuilder
                .fromUriString(gatewayUrl)
                .path("/auth/token/refresh")
                .encode()
                .build()
                .toUri();

        RequestEntity<String> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(refreshToken);

        // 토큰 요청 전송
        ResponseEntity<CommonResponse<AccessNRefreshTokenDto>> responseEntity = restTemplate.exchange(
                requestEntity, USER_RESPONSE);

        if (!responseEntity.hasBody() || !responseEntity.getBody().getHeader().isSuccessful()) {
            throw new TokenNotExistException(ErrorCode.TOKEN_NOT_EXIST);
        }
        return responseEntity.getBody();

    }
}
