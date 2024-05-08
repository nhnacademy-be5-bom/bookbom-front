package shop.bookbom.front.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.payment.adapter.PaymentAdapter;
import shop.bookbom.front.domain.payment.dto.OrderIdResponse;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentAdapter paymentAdapter;


    @Override
    public OrderIdResponse getPaymentConfirm(PaymentRequest paymentRequest) {
        return paymentAdapter.getPaymentConfirm(paymentRequest);
    }

    @Override
    public PaymentSuccessResponse orderComplete(Long orderId) {
        return paymentAdapter.orderComplete(orderId);
    }
}
