package shop.bookbom.front.domain.order.adaptor;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;

public interface OrderAdaptor {
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);
}
