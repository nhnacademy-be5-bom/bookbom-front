package shop.bookbom.front.domain.member.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDateCondition {
    private LocalDate orderDateMin;
    private LocalDate orderDateMax;
}
