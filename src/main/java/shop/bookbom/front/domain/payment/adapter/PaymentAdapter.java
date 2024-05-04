package shop.bookbom.front.domain.payment.adapter;

import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;

public interface PaymentAdapter {
    PaymentSuccessResponse getPaymentConfirm(PaymentRequest paymentRequest);
}
