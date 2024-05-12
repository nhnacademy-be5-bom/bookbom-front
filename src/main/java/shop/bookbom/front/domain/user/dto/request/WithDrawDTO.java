package shop.bookbom.front.domain.user.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithDrawDTO {
    private List<String> reasons;
}
