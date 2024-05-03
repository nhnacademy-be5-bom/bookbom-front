package shop.bookbom.front.domain.order.service;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;

public interface OrderService {

    BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    /**
     * 주문 상세 정보를 불러오는 메서드입니다.
     *
     * @param id 주문 id
     * @return 주문 상세 정보
     */
    OrderDetailResponse getOrderDetail(Long id);
}
