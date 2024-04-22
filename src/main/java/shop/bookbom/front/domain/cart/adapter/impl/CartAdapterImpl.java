package shop.bookbom.front.domain.cart.adapter.impl;

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
import shop.bookbom.front.domain.cart.adapter.CartAdapter;
import shop.bookbom.front.domain.cart.dto.CartAddRequest;
import shop.bookbom.front.domain.cart.dto.CartInfoResponse;

@Component
@RequiredArgsConstructor
public class CartAdapterImpl implements CartAdapter {
    private static final ParameterizedTypeReference<CommonResponse<CartInfoResponse>> CART_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public void addToCart(Long id, List<CartAddRequest> addItems) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<CartAddRequest>> requestEntity = new HttpEntity<>(addItems, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/{id}")
                .buildAndExpand(id)
                .toUriString();

        CommonResponse<CartInfoResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        CART_INFO_RESPONSE)
                .getBody();
        if (response != null && !response.getHeader().isSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
    }
}
