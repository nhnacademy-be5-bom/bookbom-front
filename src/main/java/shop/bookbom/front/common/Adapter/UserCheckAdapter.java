package shop.bookbom.front.common.Adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserCheckAdapter {
    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;


    private static final ParameterizedTypeReference<CommonResponse<Boolean>>
            BOOLEAN_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    public boolean checkUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        CommonResponse<Boolean> response =
                restTemplate.exchange(gatewayUrl + "/shop/open/check-user", HttpMethod.GET, requestEntity,
                        BOOLEAN_RESPONSE).getBody();
        log.info("response : ", response);
        
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RestTemplateException();
        }
        return response.getResult();

    }
}
