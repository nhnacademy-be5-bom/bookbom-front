package shop.bookbom.front.domain.order.service;

import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.PreOrderResponse;

public interface OrderService {

    PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);
}
