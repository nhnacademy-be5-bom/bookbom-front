package shop.bookbom.front.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public String handleBaseException(BaseException e, Model model) {
        model.addAttribute("errorCode", e.getErrorCode());
        model.addAttribute("message", e.getMessage());
        return "page/error";
    }
}
