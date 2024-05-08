package shop.bookbom.front.domain.order.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class OrderFailException extends BaseException {
    public OrderFailException() {
        super(ErrorCode.ORDER_IS_FAILED);
    }
}
