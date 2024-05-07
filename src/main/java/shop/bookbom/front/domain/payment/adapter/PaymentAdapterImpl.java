package shop.bookbom.front.domain.payment.adapter;

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
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.exception.OrderFailException;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;

@Component
@RequiredArgsConstructor
public class PaymentAdapterImpl implements PaymentAdapter {
    private final RestTemplate restTemplate;

    private static final ParameterizedTypeReference<CommonResponse<PaymentSuccessResponse>>
            PAYMENT_RESPONSE =
            new ParameterizedTypeReference<>() {
            };


    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    /**
     * 결제 요청
     *
     * @param paymentRequest
     * @return PaymentSuccessResponse
     */
    public PaymentSuccessResponse getPaymentConfirm(PaymentRequest paymentRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, httpHeaders);
        CommonResponse<PaymentSuccessResponse> response =
                restTemplate.exchange(gatewayUrl + "/shop/payment/tosspay/confirm"
                        , HttpMethod.POST, requestEntity, PAYMENT_RESPONSE).getBody();

        if (response == null || response.getHeader().getIsSuccessful()) {
            throw new OrderFailException();
        }
        return Objects.requireNonNull(response).getResult();

    }
}
