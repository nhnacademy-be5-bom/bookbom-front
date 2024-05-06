package shop.bookbom.front.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.order.adapter.OrderAdapter;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdapter orderAdapter;

    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        return orderAdapter.beforeOrder(beforeOrderRequestList);
    }

    @Override
    public WrapperSelectResponse selectWrapper(WrapperSelectRequest wrapperSelectRequest) {
        return orderAdapter.wrapperSelect(wrapperSelectRequest);

    }

    @Override
    public OrderResponse submitOrder(OpenOrderRequest openOrderRequest) {
        return orderAdapter.submitOrder(openOrderRequest);
    }
}
