package shop.bookbom.front.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.order.adapter.OrderAdapter;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdapter orderAdapter;

    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        return orderAdapter.beforeOrder(beforeOrderRequestList);
    }

    @Override
    public OrderDetailResponse getOrderDetail(Long id) {
        return orderAdapter.getOrderDetail(id);
    }
}
