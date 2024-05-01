package shop.bookbom.front.domain.payment.service;

import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;

public interface PaymentService {
    PaymentSuccessResponse getPaymentConfirm(PaymentRequest paymentRequest);
}
