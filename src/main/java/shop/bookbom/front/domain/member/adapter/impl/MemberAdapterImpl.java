package shop.bookbom.front.domain.member.adapter.impl;

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
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.member.adapter.MemberAdapter;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAdapterImpl implements MemberAdapter {
    private static final ParameterizedTypeReference<CommonResponse<UserInfoResponse>> MEMBER_INFO =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public UserInfoResponse getMemberInfo() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/members/my-page")
                .toUriString();

        CommonResponse<UserInfoResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        MEMBER_INFO)
                .getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
        return response.getResult();
    }
}
