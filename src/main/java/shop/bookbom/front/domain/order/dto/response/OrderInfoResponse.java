package shop.bookbom.front.domain.order.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderInfoResponse {
    private Long id;
    private LocalDate orderDate;
    private String orderNumber;
    private String name;
    private int totalCost;
    private String status;
}
