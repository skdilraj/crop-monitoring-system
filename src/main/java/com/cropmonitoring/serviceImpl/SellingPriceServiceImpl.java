package com.cropmonitoring.serviceImpl;

import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.CropCost;
import com.cropmonitoring.entity.SellingPrice;
import com.cropmonitoring.repository.CostRepository;
import com.cropmonitoring.repository.CropRepository;
import com.cropmonitoring.repository.SellingPriceRepository;
import com.cropmonitoring.service.SellingPriceService;
import org.springframework.stereotype.Service;

@Service
public class SellingPriceServiceImpl implements SellingPriceService {

    private final SellingPriceRepository sellingPriceRepository;
    private final CropRepository cropRepository;
    private final CostRepository costRepository;

    public SellingPriceServiceImpl(SellingPriceRepository sellingPriceRepository,
                                   CropRepository cropRepository,
                                   CostRepository costRepository) {
        this.sellingPriceRepository = sellingPriceRepository;
        this.cropRepository = cropRepository;
        this.costRepository = costRepository;
    }

    @Override
    public SellingPrice addOrUpdateSellingPrice(SellingPrice sellingPrice, Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        CropCost cost = costRepository.findByCrop(crop)
                .orElseThrow(() -> new RuntimeException("Production cost not found"));

        SellingPrice existing = sellingPriceRepository.findByCrop(crop).orElse(null);

        double totalSelling =
                safe(sellingPrice.getPricePerUnit()) *
                safe(sellingPrice.getQuantity());

        double totalCost = safe(cost.getTotalCost());
        double profit = totalSelling - totalCost;

        if (existing != null) {
            // 🔄 UPDATE
            existing.setPricePerUnit(sellingPrice.getPricePerUnit());
            existing.setQuantity(sellingPrice.getQuantity());
            existing.setTotalSellingAmount(totalSelling);
            existing.setProfit(profit);

            return sellingPriceRepository.save(existing);
        }

        // 🆕 CREATE
        sellingPrice.setTotalSellingAmount(totalSelling);
        sellingPrice.setProfit(profit);
        sellingPrice.setCrop(crop);

        return sellingPriceRepository.save(sellingPrice);
    }

    @Override
    public SellingPrice getSellingDetails(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        return sellingPriceRepository.findByCrop(crop)
                .orElseThrow(() -> new RuntimeException("Selling details not found"));
    }

    // 💰 PROFIT API SUPPORT
    @Override
    public Double calculateProfit(Long cropId) {
        return getSellingDetails(cropId).getProfit();
    }

    // ✅ NULL SAFE HELPER
    private double safe(Double value) {
        return value == null ? 0 : value;
    }
}
