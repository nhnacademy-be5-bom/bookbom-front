package shop.bookbom.front.domain.pointrate.adapter.impl;

import java.util.List;
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
import shop.bookbom.front.domain.pointrate.adapter.PointRateAdapter;
import shop.bookbom.front.domain.pointrate.dto.PointRate;

@Component
@RequiredArgsConstructor
public class PointRateAdapterImpl implements PointRateAdapter {
    private final RestTemplate restTemplate;
    private static final ParameterizedTypeReference<CommonResponse<List<PointRate>>>
            POINT_RATE_RESPONSE = new ParameterizedTypeReference<>() {
    };

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public List<PointRate> getPointPoilicies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/point-rate")
                .toUriString();

        CommonResponse<List<PointRate>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, POINT_RATE_RESPONSE).getBody();
        if (response == null || response.getHeader().isSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
