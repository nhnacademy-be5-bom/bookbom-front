package shop.bookbom.front.domain.order.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeforeOrderRequestList {
    private List<BeforeOrderRequest> beforeOrderRequests;
}
