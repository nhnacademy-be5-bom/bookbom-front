package shop.bookbom.front.domain.user.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.member.dto.request.OrderDateCondition;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;

public interface UserAdapter {
    Page<OrderInfoResponse> findOrderList(OrderDateCondition orderDateCondition, Pageable pageable);
}
