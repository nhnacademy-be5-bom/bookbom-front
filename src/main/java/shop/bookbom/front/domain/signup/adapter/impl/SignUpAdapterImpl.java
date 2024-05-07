package shop.bookbom.front.domain.signup.adapter.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.signup.adapter.SignUpAdapter;
import shop.bookbom.front.domain.signup.dto.SignUpDto;

@Component
@RequiredArgsConstructor
public class SignUpAdapterImpl implements SignUpAdapter {
    private final RestTemplate restTemplate;

    private static final ParameterizedTypeReference<CommonResponse<Long>>
            MEMBER_REGISTER = new ParameterizedTypeReference<>() {
    };

    @Value("${bookbom.gateway-url}")
    private String gatewayUrl;


    @Override
    public Long doRegister(SignUpDto signUpDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SignUpDto> requestEntity = new HttpEntity<>(signUpDto, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/signup")
                .toUriString();

        CommonResponse<Long> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                MEMBER_REGISTER).getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
