package shop.bookbom.front.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class BeforeOrderBookResponse {
    private Long bookId;
    private String imageUrl;
    private String title;
    private Integer quantity;
    private Integer cost;

}
