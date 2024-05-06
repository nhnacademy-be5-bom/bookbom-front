package shop.bookbom.front.domain.order.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.adapter.OrderAdapter;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderStatusUpdateRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.OrderManagementResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdapter orderAdapter;

    @Override
    public PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
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

    @Override
    public Page<OrderManagementResponse> orderManagement(Pageable pageable, String sort, String status, LocalDate dateFrom,
                                                         LocalDate dateTo) {
        return orderAdapter.getOrderManagement(pageable, dateFrom, dateTo, sort, status);
    }

    @Override
    public CommonResponse<Void> updateOrderStatus(OrderStatusUpdateRequest request) {
        return orderAdapter.updateOrderStatus(request);
    }
}
