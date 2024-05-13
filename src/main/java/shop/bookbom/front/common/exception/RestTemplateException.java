package shop.bookbom.front.common.exception;

public class RestTemplateException extends BaseException {
    public RestTemplateException() {
        super(ErrorCode.COMMON_SYSTEM_ERROR);
    }

    public RestTemplateException(String message) {
        super(ErrorCode.COMMON_SYSTEM_ERROR, message);
    }
}
