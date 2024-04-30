package shop.bookbom.front.domain.order.service;

import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;

public interface OrderService {

    PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    WrapperSelectResponse selectWrapper(WrapperSelectRequest wrapperSelectRequest);

    OrderResponse submitOrder(OpenOrderRequest openOrderRequest);
}
