package shop.bookbom.front.domain.order.dto.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrapperSelectRequest {
    private List<WrapperSelectBookRequest> wrapperSelectBookRequestList;

    @Builder
    public WrapperSelectRequest(List<WrapperSelectBookRequest> wrapperSelectBookRequestList) {
        this.wrapperSelectBookRequestList = wrapperSelectBookRequestList;
    }
}
