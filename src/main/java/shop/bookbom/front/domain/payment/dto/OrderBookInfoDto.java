package shop.bookbom.front.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderBookInfoDto {
    private String title;
    private String imgUrl;
    private Integer cost;
    private Integer quantity;
}
