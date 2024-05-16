package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
            Model model,
            HttpServletResponse response
    ) {
        boolean isLoggedIn =
                userDto != null && (userDto.getRole().equals("ROLE_MEMBER") || userDto.getRole().equals("ROLE_ADMIN"));
        String userId;
        if (isLoggedIn) {
            userId = userDto.getId().toString();
            if (cartCookie == null) {
                addCartCookie(response, userDto.getId().toString(), 10);
            }
        } else {
            userId = cartCookie;
        }
        List<CartItemDto> cartItems = cartService.getCart(userId, isLoggedIn);
        model.addAttribute("cartItems", cartItems);
        return "page/cart/cart";
    }

    /**
     * 쿠키 추가 메서드입니다.
     *
     * @param response HttpServletResponse
     * @param value    쿠키 값
     * @param days     쿠키 만료일
     */
    private void addCartCookie(HttpServletResponse response, String value, int days) {
        Cookie cookie = new Cookie("cart", value);
        cookie.setMaxAge(24 * 60 * 60 * days);
        cookie.setPath("/cart");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
