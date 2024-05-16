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
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.annotation.Login;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.dto.UserDto;
import shop.bookbom.front.common.exception.BaseException;
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
     * @param cartCookie 장바구니 쿠키
     * @param userDto    로그인 회원 정보
     * @param requests   장바구니 추가 요청
     * @param response   HttpServletResponse
     * @return
     */
    @PostMapping("/cart")
    public CommonResponse<Void> addToCart(
            @CookieValue(name = "cart", required = false) String cartCookie,
            @Login UserDto userDto,
            @RequestBody List<CartAddRequest> requests,
            HttpServletResponse response
    ) {
        boolean isLoggedIn =
                userDto != null && (userDto.getRole().equals("ROLE_MEMBER") || userDto.getRole().equals("ROLE_ADMIN"));
        String userId = UUID.randomUUID().toString();
        if (isLoggedIn) {
            userId = userDto.getId().toString();
            addCartCookie(response, userDto.getId().toString(), 10);
        } else {
            if (cartCookie != null) {
                userId = cartCookie;
            }
            addCartCookie(response, userId, 3);
        }

        cartService.addToCart(userId, requests, isLoggedIn);
        return CommonResponse.success();
    }

    /**
     * 장바구니 상품 삭제 메서드입니다.
     *
     * @param cartCookie 장바구니 쿠키
     * @param bookId     상품 ID
     */
    @DeleteMapping("/cart/items/{id}")
    public CommonResponse<Void> deleteFromCart(
            @CookieValue(name = "cart") String cartCookie,
            @PathVariable(value = "id") Long bookId,
            @Login UserDto userDto
    ) {
        boolean isLoggedIn =
                userDto != null && (userDto.getRole().equals("ROLE_MEMBER") || userDto.getRole().equals("ROLE_ADMIN"));
        String userId;
        if (isLoggedIn) {
            userId = userDto.getId().toString();
        } else {
            userId = cartCookie;
        }
        cartService.deleteItem(userId, bookId, isLoggedIn);
        return CommonResponse.success();
    }

    @PutMapping("/cart/items/{id}")
    public CommonResponse<Void> updateItem(
            @Login UserDto userDto,
            @CookieValue(name = "cart") String cartCookie,
            @PathVariable(value = "id") Long bookId,
            @RequestBody CartUpdateRequest request
    ) {
        boolean isLoggedIn = userDto != null && userDto.getRole().equals("ROLE_MEMBER");
        String userId;
        if (isLoggedIn) {
            userId = userDto.getId().toString();
        } else {
            userId = cartCookie;
        }
        int quantity = request.getQuantity();
        if (quantity < 1) {
            throw new BaseException(ErrorCode.COMMON_INVALID_PARAMETER);
        }
        cartService.updateItem(userId, bookId, quantity, isLoggedIn);
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
        cookie.setPath("/cart");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
