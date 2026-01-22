package com.cropmonitoring.controller;

import com.cropmonitoring.entity.SellingPrice;
import com.cropmonitoring.service.SellingPriceService;
import com.cropmonitoring.util.ApiResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selling")
@CrossOrigin
public class SellingPriceController {

    private final SellingPriceService sellingPriceService;

    public SellingPriceController(SellingPriceService sellingPriceService) {
        this.sellingPriceService = sellingPriceService;
    }

    // ================= CREATE / UPDATE =================
    @PostMapping("/add/{cropId}")
    public ApiResponse addSelling(@RequestBody SellingPrice sellingPrice,
                                  @PathVariable @NonNull Long cropId) {

        return new ApiResponse(
                true,
                "Selling details saved",
                sellingPriceService.addOrUpdateSellingPrice(sellingPrice, cropId)
        );
    }

    @PutMapping("/update/{cropId}")
    public ApiResponse updateSelling(@RequestBody SellingPrice sellingPrice,
                                     @PathVariable @NonNull Long cropId) {

        return new ApiResponse(
                true,
                "Selling details updated",
                sellingPriceService.addOrUpdateSellingPrice(sellingPrice, cropId)
        );
    }

    // ================= READ =================
    @GetMapping("/crop/{cropId}")
    public ApiResponse getSelling(@PathVariable @NonNull Long cropId) {

        return new ApiResponse(
                true,
                "Selling details fetched",
                sellingPriceService.getSellingDetails(cropId)
        );
    }

    // ================= 💰 PROFIT API =================
    @GetMapping("/profit/{cropId}")
    public ApiResponse getProfit(@PathVariable @NonNull Long cropId) {

        return new ApiResponse(
                true,
                "Profit calculated",
                sellingPriceService.calculateProfit(cropId)
        );
    }
}
