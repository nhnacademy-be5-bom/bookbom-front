package shop.bookbom.front.security.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class RedirectException extends BaseException {
    public RedirectException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RedirectException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
