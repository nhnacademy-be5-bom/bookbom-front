package shop.bookbom.front.domain.user.adapter.impl;

import java.util.Objects;
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
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.adapter.UserAdapter;
import shop.bookbom.front.domain.user.dto.OrderDateCondition;
import shop.bookbom.front.domain.user.dto.SignUpDto;
import shop.bookbom.front.domain.user.dto.request.SetPasswordRequest;
import shop.bookbom.front.domain.user.dto.response.SignupCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter {
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<OrderInfoResponse>>> ORDER_INFO_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<UserInfoResponse>> MEMBER_INFO =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<SignupCheckResponse>> SIGNUP_CHECK_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<Boolean>> CONFIRM_RESULT =
            new ParameterizedTypeReference<>() {
            };

    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    public Page<OrderInfoResponse> findOrderList(OrderDateCondition orderDateCondition, Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/users/orders")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("date_from", orderDateCondition.getOrderDateMin())
                .queryParam("date_to", orderDateCondition.getOrderDateMax())
                .toUriString();

        CommonResponse<CommonPage<OrderInfoResponse>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        ORDER_INFO_RESPONSE)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public UserInfoResponse getUserInfo() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/users/my-page")
                .toUriString();

        CommonResponse<UserInfoResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                MEMBER_INFO).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return response.getResult();
    }

    @Override
    public SignupCheckResponse checkNicknameCanUse(String nickname) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/users/check-nickname")
                .queryParam("nickname", nickname)
                .toUriString();

        CommonResponse<SignupCheckResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        SIGNUP_CHECK_RESPONSE)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response).getResult();
    }


    @Override
    public void setPassword(SetPasswordRequest setPasswordRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SetPasswordRequest> requestEntity = new HttpEntity<>(setPasswordRequest, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/users/edit/pw")
                .toUriString();

        CommonResponse response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity, CommonResponse.class)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
    }


    @Override
    public SignupCheckResponse checkEmailCanUse(String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/users/check-email")
                .queryParam("email", email)
                .toUriString();

        CommonResponse<SignupCheckResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        SIGNUP_CHECK_RESPONSE)
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public void signUp(SignUpDto request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SignUpDto> requestEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/sign-up")
                .toUriString();

        CommonResponse<Void> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        COMMON_RESPONSE
                )
                .getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[UserAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
    }
}
