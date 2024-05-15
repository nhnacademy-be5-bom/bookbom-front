package shop.bookbom.front.security.adapter;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.domain.signin.dto.SignInDTO;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;

@Slf4j
@RequiredArgsConstructor
public class SecurityAdapter {
    private static final ParameterizedTypeReference<CommonResponse<AccessNRefreshTokenDto>> ACCESS_N_REFRESH_TOKEN =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<String>> USER_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    public CommonResponse getTokens(SignInDTO signInDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString(gatewayUrl)
                .path("/auth/token")
                .encode()
                .build()
                .toUri();

        RequestEntity<SignInDTO> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(signInDTO);

        // 토큰 요청 전송
        CommonResponse<AccessNRefreshTokenDto> responseEntity = restTemplate.exchange(
                requestEntity, ACCESS_N_REFRESH_TOKEN).getBody();

        return responseEntity;
    }

    public CommonResponse refresh(String refreshToken) {
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
        ResponseEntity<CommonResponse<String>> responseEntity = restTemplate.exchange(
                requestEntity, USER_RESPONSE);

        if (!responseEntity.hasBody() || !responseEntity.getBody().getHeader().isSuccessful() ||
                responseEntity.getBody().getResult().isBlank()) {
            return CommonResponse.fail(ErrorCode.TOKEN_NOT_EXIST);
        }

        return responseEntity.getBody();
    }

}
