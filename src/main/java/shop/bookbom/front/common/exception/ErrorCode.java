package shop.bookbom.front.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // common
    COMMON_SYSTEM_ERROR(500, "시스템 오류가 발생했습니다. 관리자에게 문의 주세요."),
    COMMON_INVALID_PARAMETER(400, "요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND(400, "존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS(400, "잘못된 상태값입니다."),
    BEFORE_ORDER_IS_FAILED(400, "주문 전, 책정보를 불러오지 못했습니다. 다시 시도해주세요."),
    ORDER_IS_FAILED(400, "주문이 실패했습니다."),
    //order
    LOW_STOCK(400, "해당 요쳥 책의 재고가 부족합니다. 다시 시도해주세요."),
    //payment
    PAYMENT_FAILED(400, "결제가 실패했습니다"),
    PAYMENT_CANCEL_FAILED(400, "결제 취소가 실패했습니다."),
    //security
    JWT_NOT_VALIDATE(400, "유효하지 않은 상태입니다. 다시 로그인해주세요."),
    TOKEN_NOT_EXIST(400, "토큰을 생성하지 못했습니다"),
    REDIRECT_FAILED(400, "페이지 이동을 실패했습니다.");
    private final int code;
    private final String message;
}
