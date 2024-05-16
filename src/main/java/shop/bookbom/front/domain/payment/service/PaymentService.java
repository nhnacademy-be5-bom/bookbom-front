package shop.bookbom.front.domain.payment.service;

import shop.bookbom.front.domain.payment.dto.OrderIdResponse;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;

public interface PaymentService {
    OrderIdResponse getPaymentConfirm(PaymentRequest paymentRequest);

    PaymentSuccessResponse orderComplete(Long orderId);

    PaymentSuccessResponse orderFreeComplete(Long orderId);
}
