package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.cart.dto.CartItemDto;
import shop.bookbom.front.domain.cart.service.CartService;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public String getCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @RequestParam(value = "userId", required = false) String userId,
            Model model
    ) {
        if (userId == null) {
            userId = cartCookie;
        }
        List<CartItemDto> cartItems = cartService.getCart(userId);
        model.addAttribute("cartItems", cartItems);
        return "page/cart/cart";
    }
}
