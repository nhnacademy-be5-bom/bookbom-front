package shop.bookbom.front.domain.order.adapter;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.PreOrderResponse;

public interface OrderAdapter {
    public PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);
}
