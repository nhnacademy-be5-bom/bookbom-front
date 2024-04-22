package shop.bookbom.front.common;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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
}
