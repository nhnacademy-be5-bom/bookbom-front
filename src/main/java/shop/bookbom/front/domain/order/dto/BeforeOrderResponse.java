package shop.bookbom.front.domain.order.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeforeOrderResponse {
    private int TotalOrderCount;
    private List<BeforeOrderBookResponse> beforeOrderBookResponseList;

    private List<WrapperDto> wrapperList;

}
