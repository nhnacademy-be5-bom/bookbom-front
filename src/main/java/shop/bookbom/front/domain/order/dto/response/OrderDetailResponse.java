package shop.bookbom.front.domain.order.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponse {
    private Long id;
    private LocalDateTime orderDate;
    private String orderNumber;
    private String orderInfo;
    private List<OrderBookResponse> books;
    private String senderName;
    private String senderPhoneNumber;
    private String recipientName;
    private String recipientPhoneNumber;
    private DeliveryAddressDto recipientAddress;
    private int totalPrice;
    private int discountPrice;
    private int paymentPrice;
    private int wrapperPrice;
    private int usedPoint;
    private int deliveryFee;
    private String status;
}
