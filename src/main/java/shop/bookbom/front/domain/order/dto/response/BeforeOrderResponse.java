package shop.bookbom.front.domain.order.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.order.dto.request.WrapperDto;

@Getter
@NoArgsConstructor
public class BeforeOrderResponse {
    private int totalOrderCount;
    private List<BeforeOrderBookResponse> beforeOrderBookResponseList;
    private List<WrapperDto> wrapperList;

}
