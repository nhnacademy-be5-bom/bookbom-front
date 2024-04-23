package shop.bookbom.front.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WrapperDto {
    private Long id;
    private String name;
    private int cost;
}
