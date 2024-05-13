package shop.bookbom.front.domain.user.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String nickname;
    private String rank;
    private List<OrderInfoResponse> lastOrders;
    private int point;
    private long couponCount;
    private long wishCount;
}
