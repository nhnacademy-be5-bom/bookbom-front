package shop.bookbom.front.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeforeOrderRequest {
    private Long bookId;
    private Integer quantity;
}

