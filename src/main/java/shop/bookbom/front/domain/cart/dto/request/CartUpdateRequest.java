package shop.bookbom.front.domain.cart.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartUpdateRequest {
    private int quantity;
}
