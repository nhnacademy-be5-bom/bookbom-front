package shop.bookbom.front.security.exception;

import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;

public class TokenNotExistException extends BaseException {

    public TokenNotExistException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TokenNotExistException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
