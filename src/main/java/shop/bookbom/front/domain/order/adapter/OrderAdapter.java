package shop.bookbom.front.domain.order.adapter;

import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;

public interface OrderAdapter {
    PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    WrapperSelectResponse wrapperSelect(WrapperSelectRequest wrapperSelectRequest);
}
