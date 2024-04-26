package shop.bookbom.front.domain.pointrate.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.service.PointRateService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminPointRateController {
    private final PointRateService pointRateService;

    @GetMapping("/point-rate")
    public String showPointPolicy(Model model) {
        List<PointRate> pointPolicies = pointRateService.getPointPolicies();
        model.addAttribute("policies", pointPolicies);
        return "page/admin/point_rate";
    }
}
