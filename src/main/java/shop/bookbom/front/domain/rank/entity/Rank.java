package shop.bookbom.front.domain.rank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.pointrate.dto.PointRate;

@Getter
@NoArgsConstructor
public class Rank {
    private Long id;
    private String name;
    private PointRate pointRate;
}
