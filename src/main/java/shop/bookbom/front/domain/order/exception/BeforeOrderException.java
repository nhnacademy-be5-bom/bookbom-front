package shop.bookbom.front.domain.order.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class BeforeOrderException extends BaseException {
    public BeforeOrderException() {
        super(ErrorCode.BEFORE_ORDER_IS_FAILED);
    }
}
