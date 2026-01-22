package com.cropmonitoring.serviceImpl;

import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.CropCost;
import com.cropmonitoring.repository.CostRepository;
import com.cropmonitoring.repository.CropRepository;
import com.cropmonitoring.service.CostService;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl implements CostService {

    private final CostRepository costRepository;
    private final CropRepository cropRepository;

    public CostServiceImpl(CostRepository costRepository,
                           CropRepository cropRepository) {
        this.costRepository = costRepository;
        this.cropRepository = cropRepository;
    }

    @Override
    public CropCost addOrUpdateCost(CropCost cost, Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        CropCost existingCost = costRepository.findByCrop(crop).orElse(null);

        if (existingCost != null) {
            // 🔄 UPDATE
            existingCost.setSeedCost(cost.getSeedCost());
            existingCost.setFertilizerCost(cost.getFertilizerCost());
            existingCost.setLaborCost(cost.getLaborCost());
            existingCost.setIrrigationCost(cost.getIrrigationCost());
            existingCost.setOtherCost(cost.getOtherCost());

            double total =
                    safe(cost.getSeedCost()) +
                    safe(cost.getFertilizerCost()) +
                    safe(cost.getLaborCost()) +
                    safe(cost.getIrrigationCost()) +
                    safe(cost.getOtherCost());

            existingCost.setTotalCost(total);

            return costRepository.save(existingCost);
        }

        // 🆕 CREATE
        double total =
                safe(cost.getSeedCost()) +
                safe(cost.getFertilizerCost()) +
                safe(cost.getLaborCost()) +
                safe(cost.getIrrigationCost()) +
                safe(cost.getOtherCost());

        cost.setTotalCost(total);
        cost.setCrop(crop);

        return costRepository.save(cost);
    }

    @Override
    public CropCost getCostByCrop(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        return costRepository.findByCrop(crop)
                .orElseThrow(() -> new RuntimeException("Cost details not found"));
    }

    // helper
    private double safe(Double value) {
        return value == null ? 0 : value;
    }
}
