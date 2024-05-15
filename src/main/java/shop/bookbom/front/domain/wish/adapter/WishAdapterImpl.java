package shop.bookbom.front.domain.wish.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class WishAdapterImpl implements WishAdapter{
    private final ParameterizedTypeReference<CommonResponse<CommonPage<WishInfoResponse>>> WISH_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;


    @Override
    public Page<WishInfoResponse> getWishList(Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Page<WishInfoResponse>> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/wish")
                .queryParam("pageable", pageable)
                .toUriString();

        CommonResponse<CommonPage<WishInfoResponse>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        WISH_INFO_RESPONSE)
                .getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[CartAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return response.getResult();
    }
}
