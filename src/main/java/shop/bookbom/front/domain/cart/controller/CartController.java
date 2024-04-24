package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.cart.dto.CartItemDto;
import shop.bookbom.front.domain.cart.service.CartService;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public String getCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            Model model
    ) {
        // todo 로그인 처리
        boolean isLoggedIn = false;
        String userId = null;
        if (!isLoggedIn && cartCookie != null) {
            userId = cartCookie;
        }
        List<CartItemDto> cartItems = cartService.getCart(userId, isLoggedIn);
        model.addAttribute("cartItems", cartItems);
        return "page/cart/cart";
    }
}
