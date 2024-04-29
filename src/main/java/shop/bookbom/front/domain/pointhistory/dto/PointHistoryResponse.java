package shop.bookbom.front.domain.pointhistory.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointHistoryResponse {
    private Long id;
    private String reason;
    private String detail;
    private int changePoint;
    private LocalDateTime changeDate;
}
