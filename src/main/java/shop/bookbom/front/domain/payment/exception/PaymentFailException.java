package shop.bookbom.front.domain.payment.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class PaymentFailException extends BaseException {
    public PaymentFailException() {
        super(ErrorCode.PAYMENT_FAILED);
    }
}
