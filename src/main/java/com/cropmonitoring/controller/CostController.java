package com.cropmonitoring.controller;

import com.cropmonitoring.entity.CropCost;
import com.cropmonitoring.service.CostService;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cost")
@CrossOrigin
public class CostController {

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    // CREATE or UPDATE
    @PostMapping("/add/{cropId}")
    public ApiResponse addCost(@RequestBody CropCost cost,
                               @PathVariable Long cropId) {

        return new ApiResponse(
                true,
                "Cost details saved",
                costService.addOrUpdateCost(cost, cropId)
        );
    }

    // UPDATE explicitly
    @PutMapping("/update/{cropId}")
    public ApiResponse updateCost(@RequestBody CropCost cost,
                                  @PathVariable Long cropId) {

        return new ApiResponse(
                true,
                "Cost details updated",
                costService.addOrUpdateCost(cost, cropId)
        );
    }

    // GET
    @GetMapping("/crop/{cropId}")
    public ApiResponse getCost(@PathVariable Long cropId) {

        return new ApiResponse(
                true,
                "Cost details fetched",
                costService.getCostByCrop(cropId)
        );
    }
}
