package shop.bookbom.front.common;


import lombok.Builder;
import lombok.Data;

@Data
public class CommonResponse<T> {
    private static final String SUCCESS_MESSAGE = "SUCCESS";
    private ResponseHeader header;
    private T result;

    @Builder
    private CommonResponse(ResponseHeader header, T result) {
        this.header = header;
        this.result = result;
    }

}