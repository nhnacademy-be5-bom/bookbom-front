package shop.bookbom.front.domain.address.adapter.impl;

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
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.address.adapter.AddressAdapter;
import shop.bookbom.front.domain.address.dto.AddressResponse;
import shop.bookbom.front.domain.address.dto.request.AddressRequest;

@Component
@RequiredArgsConstructor
public class AddressAdapterImpl implements AddressAdapter {
    private static final ParameterizedTypeReference<CommonListResponse<AddressResponse>> ADDRESS_LIST =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public List<AddressResponse> getAddressBook() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/users/address")
                .toUriString();

        CommonListResponse<AddressResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        ADDRESS_LIST)
                .getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
        return response.getResult();
    }

    @Override
    public void saveAddress(AddressRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AddressRequest> requestEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/users/address")
                .toUriString();

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
    }
}
