package shop.bookbom.front.domain.order.adapter;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;

public interface OrderAdapter {
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequests);
}
