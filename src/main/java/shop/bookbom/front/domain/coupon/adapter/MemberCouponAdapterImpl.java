package shop.bookbom.front.domain.coupon.adapter;

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
import shop.bookbom.front.domain.coupon.dto.response.MyCouponInfoResponse;
import shop.bookbom.front.domain.coupon.dto.response.MyCouponRecordResponse;

@Component
@RequiredArgsConstructor
public class MemberCouponAdapterImpl implements MemberCouponAdapter {
    private final RestTemplate restTemplate;

    private static final ParameterizedTypeReference<CommonResponse<CommonPage<MyCouponInfoResponse>>>
            MYCOUPON_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<MyCouponRecordResponse>>>
            MYCOUPON_RECORD_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public Page<MyCouponInfoResponse> getMyConponInfo(Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Page<MyCouponInfoResponse>> requestHttpEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/mycoupons")
                .queryParam("pageable", pageable)
                .toUriString();

        CommonResponse<CommonPage<MyCouponInfoResponse>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestHttpEntity,
                        MYCOUPON_INFO_RESPONSE
                )
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RuntimeException();
        }
        return response.getResult();
    }

    @Override
    public Page<MyCouponRecordResponse> getMyCouponRecord(Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Page<MyCouponRecordResponse>> requestHttpEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/mycoupons/detail")
                .queryParam("pageable", pageable)
                .toUriString();

        CommonResponse<CommonPage<MyCouponRecordResponse>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestHttpEntity,
                        MYCOUPON_RECORD_RESPONSE
                )
                .getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
