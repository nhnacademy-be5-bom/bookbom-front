package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.cart.dto.CartAddRequest;
import shop.bookbom.front.domain.cart.service.CartService;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping("/cart")
    public CommonResponse<Void> addToCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @RequestParam(value = "userId", required = false) String userId,
            @RequestBody List<CartAddRequest> requests,
            HttpServletResponse response
    ) {
        boolean isLoggedIn = userId != null;
        if (isLoggedIn) {
            addCartCookie(response, userId, 30);
        } else {
            if (cartCookie == null) {
                userId = UUID.randomUUID().toString();
            } else {
                userId = cartCookie;
            }
            addCartCookie(response, userId, 7);
        }

        cartService.addToCart(userId, requests, isLoggedIn);
        return CommonResponse.success();
    }

    private void addCartCookie(HttpServletResponse response, String value, int days) {
        Cookie cookie = new Cookie("cart", value);
        cookie.setMaxAge(24 * 60 * 60 * days);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
