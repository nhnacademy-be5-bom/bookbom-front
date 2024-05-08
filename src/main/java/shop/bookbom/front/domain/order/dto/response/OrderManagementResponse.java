package shop.bookbom.front.domain.order.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderManagementResponse {
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private String senderName;
    private String orderInfo;
    private LocalDate expectedDeliveryDate;
    private LocalDate completeDeliveryDate;
    private String status;
}
