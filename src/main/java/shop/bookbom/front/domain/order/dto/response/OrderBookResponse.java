package shop.bookbom.front.domain.order.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderBookResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private int price;
    private int quantity;
    private String status;
}
