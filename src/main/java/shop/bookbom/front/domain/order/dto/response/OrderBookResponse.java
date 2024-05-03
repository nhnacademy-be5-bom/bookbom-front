package shop.bookbom.front.domain.order.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderBookResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private int bookPrice;
    private int quantity;
    private boolean isPackaging;
    private int wrapperCost;
    private String status;
}
