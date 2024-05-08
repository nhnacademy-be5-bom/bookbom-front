package shop.bookbom.front.domain.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WrapperSelectBookRequest {
    private Long bookId;
    private String wrapperName;
    private Integer quantity;

}

