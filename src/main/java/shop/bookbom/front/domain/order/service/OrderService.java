package shop.bookbom.front.domain.order.service;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;

public interface OrderService {

    BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);
}
