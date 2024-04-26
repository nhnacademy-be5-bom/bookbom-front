package shop.bookbom.front.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class WrapperDto {
    private Long id;
    private String name;
    private int cost;
}
