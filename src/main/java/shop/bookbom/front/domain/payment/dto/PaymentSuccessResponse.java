package shop.bookbom.front.domain.payment.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentSuccessResponse {
    private String orderNumber;
    private String orderInfo;
    private Integer totalCount;
    private List<OrderBookInfoDto> orderBookInfoDtoList;
    private Integer totalAmount;
    private String paymentMethodName;
    private String deliveryName;
    private String deliveryPhoneNumber;
    private String zipCode;
    private String deliveryAddress;
    private String addressDetail;

}
