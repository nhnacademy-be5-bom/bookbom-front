package shop.bookbom.front.domain.cart.adapter.impl;

import java.util.List;
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
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.cart.adapter.CartAdapter;
import shop.bookbom.front.domain.cart.dto.request.CartAddRequest;
import shop.bookbom.front.domain.cart.dto.request.CartUpdateRequest;
import shop.bookbom.front.domain.cart.dto.response.CartInfoResponse;
import shop.bookbom.front.domain.cart.dto.response.CartUpdateResponse;

@Component
@RequiredArgsConstructor
public class CartAdapterImpl implements CartAdapter {
    private static final ParameterizedTypeReference<CommonResponse<CartInfoResponse>> CART_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonListResponse<Long>> CART_ITEM_IDS_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<CartUpdateResponse>> CART_UPDATE_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public List<Long> addToCart(Long id, List<CartAddRequest> addItems) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<CartAddRequest>> requestEntity = new HttpEntity<>(addItems, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/{id}")
                .buildAndExpand(id)
                .toUriString();

        CommonListResponse<Long> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        CART_ITEM_IDS_RESPONSE)
                .getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public CartInfoResponse getCart(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/{id}")
                .buildAndExpand(userId)
                .toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);


        CommonResponse<CartInfoResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        CART_INFO_RESPONSE)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RestTemplateException();
        }
        return response.getResult();
    }

    @Override
    public CartUpdateResponse updateCart(Long id, int quantity) {
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/items/{id}")
                .buildAndExpand(id)
                .toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartUpdateRequest> requestEntity = new HttpEntity<>(new CartUpdateRequest(quantity), httpHeaders);

        CommonResponse<CartUpdateResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        CART_UPDATE_RESPONSE)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RestTemplateException();
        }
        return response.getResult();
    }

    @Override
    public void deleteCart(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/items/{id}")
                .buildAndExpand(id)
                .toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RestTemplateException();
        }
    }
}
