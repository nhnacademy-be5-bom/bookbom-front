package shop.bookbom.front.domain.admin.coupon.adapter;

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
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.admin.coupon.dto.CouponPolicyInfoDto;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyAddRequest;
import shop.bookbom.front.domain.admin.coupon.dto.request.CouponPolicyDeleteRequest;

@Component
@RequiredArgsConstructor
public class AdminCouponAdapterImpl implements AdminCouponAdapter {
    private final RestTemplate restTemplate;

    //@Value("${bookbom.gateway-url}")
    String gatewayUrl = "http://127.0.0.1:8011";

    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonListResponse<CouponPolicyInfoDto>>
            COUPONPOLICY_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    @Override
    public void addCouponPolicy(CouponPolicyAddRequest request, Long userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CouponPolicyAddRequest> requestHttpEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/couponPolicy/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestHttpEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            //예외처리
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCouponPolicy(CouponPolicyDeleteRequest request, Long userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CouponPolicyDeleteRequest> requestHttpEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/couponPolicy/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.DELETE,
                        requestHttpEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            //예외처리
            throw new RuntimeException();
        }
    }

    @Override
    public void updateCouponPolicy(CouponPolicyInfoDto request, Long userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CouponPolicyInfoDto> requestHttpEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/couponPolicy/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.PUT,
                        requestHttpEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            //예외처리
            throw new RuntimeException();
        }
    }

    @Override
    public List<CouponPolicyInfoDto> getCouponPolicyInfo(Long userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CouponPolicyInfoDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/couponPolicy/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        CommonListResponse<CouponPolicyInfoDto> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestHttpEntity,
                        COUPONPOLICY_INFO_RESPONSE)
                .getBody();
        if (response == null || response.getHeader().getIsSuccessful()) {
            throw new RuntimeException();
        }
        return response.getResult();
    }
}
