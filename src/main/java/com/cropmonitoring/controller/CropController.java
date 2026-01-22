package com.cropmonitoring.controller;


import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.service.CropService;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@CrossOrigin
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    // ADD CROP
    @PostMapping("/add/{farmerId}")
    public ApiResponse addCrop(@RequestBody Crop crop,
                               @PathVariable Long farmerId) {

        Crop savedCrop = cropService.addCrop(crop, farmerId);

        return new ApiResponse(true, "Crop added successfully", savedCrop);
    }

    // GET CROPS BY FARMER
    @GetMapping("/farmer/{farmerId}")
    public ApiResponse getCrops(@PathVariable Long farmerId) {

        List<Crop> crops = cropService.getCropsByFarmer(farmerId);

        return new ApiResponse(true, "Crops fetched", crops);
    }
}
