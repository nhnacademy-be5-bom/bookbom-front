package shop.bookbom.front.domain.signin.adaptor.impl;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.signin.adaptor.SignInAdaptor;
import shop.bookbom.front.domain.signin.dto.SignInDTO;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignInAdapterImpl implements SignInAdaptor {
    private static final ParameterizedTypeReference<CommonResponse<AccessNRefreshTokenDto>> ACCESS_N_REFRESH_TOKEN =
            new ParameterizedTypeReference<>() {
            };

    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public AccessNRefreshTokenDto signIn(SignInDTO signInDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString(gatewayUrl)
                .path("/auth/token")
                .encode()
                .build()
                .toUri();

        log.info(uri.toString());
        RequestEntity<SignInDTO> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(signInDTO);

        // 토큰 요청 전송
        ResponseEntity<CommonResponse<AccessNRefreshTokenDto>> responseEntity = restTemplate.exchange(
                requestEntity, ACCESS_N_REFRESH_TOKEN);

        log.info(requestEntity.getBody().getEmail());

        return responseEntity.getBody().getResult();
    }
}
