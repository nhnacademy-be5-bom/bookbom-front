package shop.bookbom.front.security.exception;

import org.springframework.security.core.AuthenticationException;
import shop.bookbom.front.common.exception.ErrorCode;

public class SignInFailedException extends AuthenticationException {
    public SignInFailedException() {
        super(ErrorCode.SIGN_IN_FAILED.getMessage());
    }
}
