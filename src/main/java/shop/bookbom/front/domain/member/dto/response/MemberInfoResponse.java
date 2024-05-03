package shop.bookbom.front.domain.member.dto.response;

import java.util.List;
import lombok.Getter;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;

@Getter
public class MemberInfoResponse {
    private Long id;
    private String nickname;
    private String rank;
    private List<OrderInfoResponse> lastOrders;
    private int point;
    private long couponCount;
    private long wishCount;
}
