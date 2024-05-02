package shop.bookbom.front.domain.cart.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDto implements Serializable {
    private Long id;
    private Long bookId;
    private String thumbnail;
    private String title;
    private int price;
    private int discountPrice;
    private int quantity;

    @Builder
    public CartItemDto(
            Long id,
            Long bookId,
            String thumbnail,
            String title,
            int price,
            int discountPrice,
            int quantity
    ) {
        this.id = id;
        this.bookId = bookId;
        this.thumbnail = thumbnail;
        this.title = title;
        this.price = price;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
    }

    public static CartItemDto of(
            Long id,
            Long bookId,
            String thumbnail,
            String title,
            int price,
            int discountPrice,
            int quantity
    ) {
        return CartItemDto.builder()
                .id(id)
                .bookId(bookId)
                .thumbnail(thumbnail)
                .title(title)
                .price(price)
                .discountPrice(discountPrice)
                .quantity(quantity)
                .build();
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
