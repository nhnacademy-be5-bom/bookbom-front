package shop.bookbom.front.domain.pointhistory.adapter.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.pointhistory.adapter.PointHistoryAdapter;
import shop.bookbom.front.domain.pointhistory.dto.PointHistoryResponse;

@Component
@RequiredArgsConstructor
public class PointHistoryAdapterImpl implements PointHistoryAdapter {
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<PointHistoryResponse>>>
            POINT_HISTORY_RESPONSE = new ParameterizedTypeReference<>() {
    };

    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public Page<PointHistoryResponse> findPointHistory(Pageable pageable, String reason) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/member/point-history")
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("userId", 1L); // todo 회원 처리
        if (reason != null) {
            uriBuilder.queryParam("reason", reason);
        }
        String url = uriBuilder.build().toUriString();

        CommonResponse<CommonPage<PointHistoryResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, POINT_HISTORY_RESPONSE).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return Objects.requireNonNull(response).getResult();
    }
}
