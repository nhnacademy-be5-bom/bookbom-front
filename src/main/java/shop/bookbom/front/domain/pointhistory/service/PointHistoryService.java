package shop.bookbom.front.domain.pointhistory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.pointhistory.dto.PointHistoryResponse;

public interface PointHistoryService {
    Page<PointHistoryResponse> findPointHistory(Pageable pageable, String reason);
}
