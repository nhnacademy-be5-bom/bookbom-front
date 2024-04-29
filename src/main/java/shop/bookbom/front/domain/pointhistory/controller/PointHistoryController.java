package shop.bookbom.front.domain.pointhistory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.pointhistory.dto.PointHistoryResponse;
import shop.bookbom.front.domain.pointhistory.service.PointHistoryService;

@Controller
@RequiredArgsConstructor
public class PointHistoryController {
    private final PointHistoryService pointHistoryService;

    @GetMapping("/member/point-history")
    public String pointHistory(
            Model model,
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String reason
    ) {
        Page<PointHistoryResponse> result = pointHistoryService.findPointHistory(pageable, reason);
        model.addAttribute("content", result.getContent());
        model.addAttribute("currentPage", result.getNumber());
        model.addAttribute("pageSize", result.getPageable().getPageSize());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("totalItems", result.getTotalElements());
        model.addAttribute("size", result.getSize());
        return "member/point-history";
    }
}
