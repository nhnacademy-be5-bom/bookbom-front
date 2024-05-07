package shop.bookbom.front.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCheckResponse {
    private boolean canUse;
}
