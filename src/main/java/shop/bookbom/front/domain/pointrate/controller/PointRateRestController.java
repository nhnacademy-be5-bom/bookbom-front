package shop.bookbom.front.domain.pointrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.dto.request.PointRateUpdateRequest;
import shop.bookbom.front.domain.pointrate.service.PointRateService;

    @RestController
    @RequiredArgsConstructor
    public class PointRateRestController {
        private final PointRateService pointRateService;


        @PutMapping("/point-rate/{id}")
        public PointRate updatePointPolicy(
                @PathVariable("id") Long id,
                @RequestBody PointRateUpdateRequest request
        ) {
            return pointRateService.updatePolicy(id, request);
        }
    }
