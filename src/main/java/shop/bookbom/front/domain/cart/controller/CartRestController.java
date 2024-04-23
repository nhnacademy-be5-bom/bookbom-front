package shop.bookbom.front.domain.cart.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.domain.cart.dto.request.CartAddRequest;
import shop.bookbom.front.domain.cart.dto.request.CartUpdateRequest;
import shop.bookbom.front.domain.cart.service.CartService;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    /**
     * 장바구니에 상품 추가 메서드입니다.
     *
     * @param cartCookie
     * @param userId
     * @param requests
     * @param response
     * @return
     */
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

    /**
     * 장바구니 상품 삭제 메서드입니다.
     *
     * @param cartCookie 장바구니 쿠키
     * @param userId     사용자 ID
     * @param itemId     상품 ID
     */
    @DeleteMapping("/cart/items/{id}")
    public CommonResponse<Void> deleteFromCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @RequestParam(value = "userId", required = false) String userId,
            @PathVariable(value = "id") Long itemId
    ) {
        boolean isLoggedIn = userId != null;
        if (cartCookie != null) {
            userId = cartCookie;
        }
        cartService.deleteItem(userId, itemId, isLoggedIn);
        return CommonResponse.success();
    }

    @PutMapping("/cart/items/{id}")
    public CommonResponse<Void> updateItem(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @RequestParam(value = "userId", required = false) String userId,
            @PathVariable(value = "id") Long itemId,
            @RequestBody CartUpdateRequest request
    ) {
        int quantity = request.getQuantity();
        if (quantity < 1) {
            return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER);
        }
        boolean isLoggedIn = userId != null;
        if (cartCookie != null) {
            userId = cartCookie;
        }
        cartService.updateItem(userId, itemId, quantity, isLoggedIn);
        return CommonResponse.success();
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
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
