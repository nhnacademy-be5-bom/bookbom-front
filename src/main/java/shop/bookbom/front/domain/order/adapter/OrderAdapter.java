package shop.bookbom.front.domain.order.adapter;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;

public interface OrderAdapter {
    /**
     * 주문 상세 정보를 불러오는 메서드입니다.
     *
     * @param id 주문 id
     * @return 주문 상세 정보
     */
    OrderDetailResponse getOrderDetail(Long id);

    PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    WrapperSelectResponse wrapperSelect(WrapperSelectRequest wrapperSelectRequest);

    OrderResponse submitOrder(OpenOrderRequest openOrderRequest);
}
