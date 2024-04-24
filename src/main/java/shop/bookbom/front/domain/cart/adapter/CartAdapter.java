package shop.bookbom.front.domain.cart.adapter;

import java.util.List;
import shop.bookbom.front.domain.cart.dto.request.CartAddRequest;
import shop.bookbom.front.domain.cart.dto.request.CartUpdateRequest;
import shop.bookbom.front.domain.cart.dto.response.CartInfoResponse;
import shop.bookbom.front.domain.cart.dto.response.CartUpdateResponse;

public interface CartAdapter {
    /**
     * 장바구니에 상품을 저장하는 메서드입니다.
     * @param id 회원 ID
     * @param addItems 저장 상품(상품 id, 수량)
     */
    List<Long> addToCart(Long id, List<CartAddRequest> addItems);

    /**
     * 장바구니에서 상품을 가져오는 메서드입니다.
     *
     * @param userId 회원 ID
     * @return CartInfoResponse 장바구니 상품 ID, 수량
     */
    CartInfoResponse getCart(Long userId);

    /**
     * 장바구니에서 상품 수량을 변경하는 메서드입니다.
     * @param id 장바구니 상품 ID
     * @param quantity 상품 수량
     * @return CartUpdateResponse
     */
    CartUpdateResponse updateCart(Long id, int quantity);

    /**
     * 장바구니에서 상품을 삭제하는 메서드입니다.
     * @param id 장바구니 상품 ID
     */
    void deleteCart(Long id);
}
