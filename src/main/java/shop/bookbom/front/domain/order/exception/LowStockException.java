package shop.bookbom.front.domain.order.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class LowStockException extends BaseException {
    public LowStockException() {
        super(ErrorCode.LOW_STOCK);
    }

    public LowStockException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
