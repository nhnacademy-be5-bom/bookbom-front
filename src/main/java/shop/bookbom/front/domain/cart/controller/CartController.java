package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.annotation.Login;
import shop.bookbom.front.common.dto.UserDto;
import shop.bookbom.front.domain.cart.dto.CartItemDto;
import shop.bookbom.front.domain.cart.service.CartService;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public String getCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @Login UserDto userDto,
            Model model
    ) {
        boolean isLoggedIn = userDto != null && userDto.getRole().equals("ROLE_MEMBER");
        String userId = "";
        if (userDto == null) {
            userId = cartCookie;
        } else if (isLoggedIn) {
            userId = userDto.getId().toString();
        }
        List<CartItemDto> cartItems = cartService.getCart(userId, isLoggedIn);
        model.addAttribute("cartItems", cartItems);
        return "page/cart/cart";
    }
}
