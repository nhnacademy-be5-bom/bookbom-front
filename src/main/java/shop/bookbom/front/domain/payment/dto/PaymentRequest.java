package shop.bookbom.front.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private String orderId;
    private String paymentKey;
    private Integer amount;
}
