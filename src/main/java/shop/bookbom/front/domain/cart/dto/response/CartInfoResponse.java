package shop.bookbom.front.domain.cart.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.cart.dto.CartItemDto;

@Getter
@NoArgsConstructor
public class CartInfoResponse {
    private Long cartId;
    private List<CartItemDto> cartItems;
}


