package shop.bookbom.front.domain.cart.adapter;

import java.util.List;
import shop.bookbom.front.domain.cart.dto.CartAddRequest;

public interface CartAdapter {
    /**
     * 장바구니에 상품을 저장하는 메서드입니다.
     *
     * @param id       회원 ID
     * @param addItems 저장 상품(상품 id, 수량)
     */
    void addToCart(Long id, List<CartAddRequest> addItems);
}
