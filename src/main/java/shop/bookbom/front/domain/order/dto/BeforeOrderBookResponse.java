package shop.bookbom.front.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeforeOrderBookResponse {
    private Long bookId;
    private String imageUrl;
    private String title;
    private Integer quantity;
    private Integer cost;

}

