package shop.bookbom.front.common;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import shop.bookbom.front.common.exception.ErrorCode;

@Getter
@NoArgsConstructor
public class CommonResponse<T> {
    private static final String SUCCESS_MESSAGE = "SUCCESS";
    private ResponseHeader header;
    private T result;

    @Builder
    private CommonResponse(ResponseHeader header, T result) {
        this.header = header;
        this.result = result;
    }

    public static CommonResponse<Void> success() {
        return CommonResponse.<Void>builder()
                .header(ResponseHeader.builder()
                        .isSuccessful(true)
                        .resultCode(HttpStatus.OK.value())
                        .resultMessage(SUCCESS_MESSAGE).build())
                .build();
    }

    public static CommonResponse<Void> fail(ErrorCode errorCode) {
        return CommonResponse.<Void>builder()
                .header(ResponseHeader.builder()
                        .isSuccessful(false)
                        .resultCode(errorCode.getCode())
                        .resultMessage(errorCode.getMessage()).build())
                .build();
    }
}
