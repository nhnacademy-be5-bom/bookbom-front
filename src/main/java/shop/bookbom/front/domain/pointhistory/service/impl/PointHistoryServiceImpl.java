package shop.bookbom.front.domain.pointhistory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.pointhistory.adapter.PointHistoryAdapter;
import shop.bookbom.front.domain.pointhistory.dto.PointHistoryResponse;
import shop.bookbom.front.domain.pointhistory.service.PointHistoryService;

@Service
@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService {
    private final PointHistoryAdapter pointHistoryAdapter;

    @Override
    public Page<PointHistoryResponse> findPointHistory(Pageable pageable, String reason) {
        return pointHistoryAdapter.findPointHistory(pageable, reason);
    }
}
