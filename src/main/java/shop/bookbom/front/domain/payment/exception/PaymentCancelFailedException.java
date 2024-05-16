package shop.bookbom.front.domain.payment.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class PaymentCancelFailedException extends BaseException {
    public PaymentCancelFailedException() {
        super(ErrorCode.PAYMENT_CANCEL_FAILED);
    }
}
