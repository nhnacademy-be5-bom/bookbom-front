package shop.bookbom.front.domain.pointrate.adapter.impl;

import java.util.List;
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
import shop.bookbom.front.domain.pointrate.adapter.PointRateAdapter;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.dto.request.PointRateUpdateRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointRateAdapterImpl implements PointRateAdapter {
    private final RestTemplate restTemplate;
    private static final ParameterizedTypeReference<CommonResponse<List<PointRate>>>
            POINT_RATE_LIST_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<CommonResponse<PointRate>>
            POINT_RATE_RESPONSE = new ParameterizedTypeReference<>() {
    };

    @Value("${bookbom.gateway-url}")
    private String gatewayUrl;

    @Override
    public List<PointRate> getPointPolicies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/point-rate")
                .toUriString();

        CommonResponse<List<PointRate>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, POINT_RATE_LIST_RESPONSE).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[PointRateAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return response.getResult();
    }

    @Override
    public PointRate updatePolicy(Long id, PointRateUpdateRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PointRateUpdateRequest> requestEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/point-rate/{id}")
                .buildAndExpand(id)
                .toUriString();

        CommonResponse<PointRate> response =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, POINT_RATE_RESPONSE).getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[PointRateAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return response.getResult();
    }
}
