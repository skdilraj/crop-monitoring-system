package com.cropmonitoring.controller;


import com.cropmonitoring.entity.EnvironmentData;
import com.cropmonitoring.service.EnvironmentService;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/environment")
@CrossOrigin
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    // ADD ENVIRONMENT DATA
    @PostMapping("/add/{cropId}")
    public ApiResponse addData(@RequestBody EnvironmentData data,
                               @PathVariable Long cropId) {

        EnvironmentData saved = environmentService.addEnvironmentData(data, cropId);

        return new ApiResponse(true, "Environment data added", saved);
    }

    // GET ENVIRONMENT DATA BY CROP
    @GetMapping("/crop/{cropId}")
    public ApiResponse getData(@PathVariable Long cropId) {

        List<EnvironmentData> list = environmentService.getEnvironmentByCrop(cropId);

        return new ApiResponse(true, "Environment data fetched", list);
    }
}