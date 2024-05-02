package shop.bookbom.front.domain.cart.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartAddRequest {
    private Long bookId;
    private String thumbnail;
    private String title;
    private int price;
    private int discountPrice;
    private int quantity;
}
