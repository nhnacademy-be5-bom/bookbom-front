package shop.bookbom.front.domain.order.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    private List<String> orderIds;
    private String status;
}
