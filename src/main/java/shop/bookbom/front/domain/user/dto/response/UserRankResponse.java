package shop.bookbom.front.domain.user.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.rank.entity.dto.request.RankRequest;
import shop.bookbom.front.domain.rank.entity.Rank;

@Getter
@NoArgsConstructor
public class UserRankResponse {
    private String nickname;
    private String userrank;
    private List<RankRequest> ranks;
}
