package shop.bookbom.front.domain.member.adapter;

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
import shop.bookbom.front.domain.member.dto.response.MemberInfoResponse;

@Component
@RequiredArgsConstructor
public class MemberAdapterImpl implements MemberAdapter {
    private static final ParameterizedTypeReference<CommonResponse<MemberInfoResponse>> MEMBER_INFO =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public MemberInfoResponse myPage() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/member/my-page")
                // todo gateway 구현되면 queryParam 제거
                .queryParam("userId", 1)
                .toUriString();

        CommonResponse<MemberInfoResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                MEMBER_INFO).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
