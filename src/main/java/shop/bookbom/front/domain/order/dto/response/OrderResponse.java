package shop.bookbom.front.domain.order.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private String orderName;
    private String orderId;
    private Integer amount;

}
