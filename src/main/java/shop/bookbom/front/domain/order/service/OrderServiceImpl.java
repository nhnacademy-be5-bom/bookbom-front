package shop.bookbom.front.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.order.adaptor.OrderAdaptor;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdaptor orderAdaptor;

    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        return orderAdaptor.beforeOrder(beforeOrderRequestList);
    }
}
