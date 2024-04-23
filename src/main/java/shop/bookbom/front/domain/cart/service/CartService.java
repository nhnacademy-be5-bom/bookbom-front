package shop.bookbom.front.domain.cart.service;

import java.util.List;
import shop.bookbom.front.domain.cart.dto.CartItemDto;
import shop.bookbom.front.domain.cart.dto.request.CartAddRequest;

public interface CartService {
    /**
     * 장바구니에 상품을 저장하는 메서드입니다.
     *
     * @param id         회원 ID(비회원인 경우는 UUID)
     * @param requests   저장 상품(상품 id, 수량)
     * @param isLoggedIn 로그인 여부
     */
    void addToCart(String id, List<CartAddRequest> requests, boolean isLoggedIn);

    /**
     * 장바구니 상품을 조회하는 메서드입니다.
     *
     * @param id 회원 ID(비회원인 경우는 UUID)
     * @return CartResponse
     */
    List<CartItemDto> getCart(String id);

    /**
     * 장바구니 상품을 삭제하는 메서드입니다.
     *
     * @param userId     회원 ID(비회원인 경우는 UUID)
     * @param itemId     상품 ID
     * @param isLoggedIn 로그인 여부
     */
    void deleteItem(String userId, Long itemId, boolean isLoggedIn);

    void updateItem(String userId, Long itemId, int quantity, boolean isLoggedIn);
}
