package shop.bookbom.front.domain.order.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeforeOrderRequestList {
    private List<BeforeOrderRequest> beforeOrderRequestList;
}
