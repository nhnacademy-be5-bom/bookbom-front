package shop.bookbom.front.domain.order.adapter;

import java.time.LocalDate;
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
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderStatusUpdateRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OpenWrapperSelectResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.dto.response.OrderManagementResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;
import shop.bookbom.front.domain.order.exception.BeforeOrderException;
import shop.bookbom.front.domain.order.exception.LowStockException;
import shop.bookbom.front.domain.order.exception.OrderFailException;
import shop.bookbom.front.domain.payment.dto.OrderIdResponse;
import shop.bookbom.front.domain.payment.dto.response.PaymentCancelResponse;
import shop.bookbom.front.domain.payment.exception.PaymentCancelFailedException;

@Slf4j
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
    private static final ParameterizedTypeReference<CommonResponse<OpenWrapperSelectResponse>>
            WRAPPER_SELECT_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private static final ParameterizedTypeReference<CommonResponse<WrapperSelectResponse>>
            WRAPPER_SELECT_MEMBER_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private static final ParameterizedTypeReference<CommonResponse<OrderResponse>>
            ORDER_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private static final ParameterizedTypeReference<CommonResponse<CommonPage<OrderManagementResponse>>>
            ORDER_MANAGEMENT_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private static final ParameterizedTypeReference<CommonResponse<Void>>
            COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private static final ParameterizedTypeReference<CommonResponse<OrderIdResponse>>
            ORDER_ID_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<PaymentCancelResponse>>
            PAYMENT_CANCEL_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    /**
     * 주문 정보 불러오기
     *
     * @param beforeOrderRequestList
     * @return
     */
    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BeforeOrderRequestList> requestEntity =
                new HttpEntity<>(beforeOrderRequestList, httpHeaders);


        CommonResponse<BeforeOrderResponse> response =
                restTemplate.exchange(gatewayUrl + "/shop/open/orders/before-order"
                        , HttpMethod.POST, requestEntity, BEFORE_ORDER_RESPONSE).getBody();
        if (response == null) {
            throw new BeforeOrderException();
        }
        //exception 이면 failResponse 빌더를 만듬
        if (!response.getHeader().isSuccessful()) {
            throw new LowStockException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    /**
     * 포장지 응답 받기
     *
     * @param wrapperSelectRequest
     * @return
     */
    @Override
    public OpenWrapperSelectResponse wrapperSelect(WrapperSelectRequest wrapperSelectRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WrapperSelectRequest> requestEntity =
                new HttpEntity<>(wrapperSelectRequest, httpHeaders);


        CommonResponse<OpenWrapperSelectResponse> response =
                restTemplate.exchange(gatewayUrl + "/shop/open/orders/wrapper"
                        , HttpMethod.POST, requestEntity, WRAPPER_SELECT_RESPONSE).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new BeforeOrderException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public WrapperSelectResponse wrapperSelectForMember(WrapperSelectRequest wrapperSelectRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WrapperSelectRequest> requestEntity =
                new HttpEntity<>(wrapperSelectRequest, httpHeaders);


        CommonResponse<WrapperSelectResponse> response =
                restTemplate.exchange(gatewayUrl + "/shop/orders/wrapper"
                        , HttpMethod.POST, requestEntity, WRAPPER_SELECT_MEMBER_RESPONSE).getBody();
        if (response == null || !response.getHeader().isSuccessful()) {
            throw new BeforeOrderException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    /**
     * 주문 정보 전달
     *
     * @param openOrderRequest
     * @return
     */
    @Override
    public OrderResponse submitOrder(OpenOrderRequest openOrderRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OpenOrderRequest> requestEntity = new HttpEntity<>(openOrderRequest, httpHeaders);

        CommonResponse<OrderResponse> response = restTemplate.exchange(gatewayUrl + "/shop/open/orders"
                , HttpMethod.POST, requestEntity, ORDER_RESPONSE).getBody();
        if (response == null) {
            throw new OrderFailException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new LowStockException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    @Override
    public OrderResponse submitMemberOrder(OrderRequest orderRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest, httpHeaders);

        CommonResponse<OrderResponse> response = restTemplate.exchange(gatewayUrl + "/shop/orders"
                , HttpMethod.POST, requestEntity, ORDER_RESPONSE).getBody();
        if (response == null) {
            throw new OrderFailException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new OrderFailException(response.getHeader().getResultMessage());
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
        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[OrderAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return response.getResult();
    }

    /**
     * 관리자 주문 관리 페이지를 위해 주문 정보를 받아오는 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param dateFrom 주문 검색 시작 날짜
     * @param dateTo   주문 검색 종료 날짜
     * @param sort     정렬 기준
     * @param status   주문 상태
     * @return 주문 정보 페이지
     */
    @Override
    public Page<OrderManagementResponse> getOrderManagement(Pageable pageable, LocalDate dateFrom, LocalDate dateTo,
                                                            String sort, String status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/admin/orders")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sorted", sort)
                .queryParam("status", status)
                .toUriString();

        CommonResponse<CommonPage<OrderManagementResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, ORDER_MANAGEMENT_RESPONSE).getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[OrderAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response).getResult();
    }

    /**
     * 주문 상태를 변경하는 메서드입니다.
     *
     * @param request 주문 상태를 변경할 주문 ID 리스트, 변경할 주문 상태
     * @return 주문 상태 변경 결과
     */
    @Override
    public CommonResponse<Void> updateOrderStatus(OrderStatusUpdateRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderStatusUpdateRequest> requestEntity = new HttpEntity<>(request, httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/admin/orders/update-status")
                .toUriString();

        CommonResponse<Void> response =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, COMMON_RESPONSE).getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            log.error("[OrderAdapter] errorMessage : {}", response.getHeader().getResultMessage());
            throw new RestTemplateException();
        }
        return Objects.requireNonNull(response);
    }

    @Override
    public OrderIdResponse processFreePayment(String orderNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> requestEntity = new HttpEntity<>(orderNumber, httpHeaders);

        CommonResponse<OrderIdResponse> response = restTemplate.exchange(gatewayUrl + "/shop/open/payment/free"
                , HttpMethod.POST, requestEntity, ORDER_ID_RESPONSE).getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            throw new OrderFailException();
        }
        return Objects.requireNonNull(response).getResult();

    }

    @Override
    public PaymentCancelResponse cancelOrder(Long orderId, String cancelReason) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        CommonResponse<PaymentCancelResponse> response =
                restTemplate.exchange(gatewayUrl + "/shop/orders/{id}?reason=" + cancelReason
                        , HttpMethod.DELETE, requestEntity, PAYMENT_CANCEL_RESPONSE, orderId).getBody();

        if (response == null || !response.getHeader().isSuccessful()) {
            throw new PaymentCancelFailedException();
        }
        return Objects.requireNonNull(response).getResult();

    }
}
