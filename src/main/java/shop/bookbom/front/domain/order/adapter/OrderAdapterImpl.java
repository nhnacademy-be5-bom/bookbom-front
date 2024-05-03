package shop.bookbom.front.domain.order.adapter;

import java.util.Objects;
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
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.exception.BeforeOrderException;

@Component
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderAdapter {
    private final RestTemplate restTemplate;

    private static final ParameterizedTypeReference<CommonResponse<BeforeOrderResponse>>
            BEFORE_ORDER_RESPONSE =
            new ParameterizedTypeReference<>() {
            };


    private static final ParameterizedTypeReference<CommonResponse<OrderDetailResponse>> ORDER_DETAIL_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BeforeOrderRequestList> requestEntity =
                new HttpEntity<>(beforeOrderRequestList, httpHeaders);


        CommonResponse<BeforeOrderResponse> response = restTemplate.exchange(gatewayUrl + "/shop/orders/before-order"
                , HttpMethod.POST, requestEntity, BEFORE_ORDER_RESPONSE).getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            throw new BeforeOrderException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public OrderDetailResponse getOrderDetail(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/orders/{id}")
                .buildAndExpand(id)
                .toUriString();

        CommonResponse<OrderDetailResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                ORDER_DETAIL_RESPONSE).getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
