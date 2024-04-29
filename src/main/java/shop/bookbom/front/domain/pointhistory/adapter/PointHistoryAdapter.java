package shop.bookbom.front.domain.pointhistory.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.pointhistory.dto.ChangeReason;
import shop.bookbom.front.domain.pointhistory.dto.PointHistoryResponse;

public interface PointHistoryAdapter {
    Page<PointHistoryResponse> findPointHistory(Pageable pageable, ChangeReason reason);
}
