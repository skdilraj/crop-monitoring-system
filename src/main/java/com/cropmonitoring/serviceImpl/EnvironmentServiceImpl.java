package com.cropmonitoring.serviceImpl;


import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.EnvironmentData;
import com.cropmonitoring.repository.CropRepository;
import com.cropmonitoring.repository.EnvironmentRepository;
import com.cropmonitoring.service.EnvironmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final CropRepository cropRepository;

    public EnvironmentServiceImpl(EnvironmentRepository environmentRepository,
                                  CropRepository cropRepository) {
        this.environmentRepository = environmentRepository;
        this.cropRepository = cropRepository;
    }

    @Override
    public EnvironmentData addEnvironmentData(EnvironmentData data, Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        // ===== HEALTH LOGIC =====
        String health;
        if (data.getSoilMoisture() < 30) {
            health = "CRITICAL";
        } else if (data.getTemperature() > 38) {
            health = "WARNING";
        } else {
            health = "HEALTHY";
        }

        data.setHealthStatus(health);
        data.setRecordedAt(LocalDateTime.now());
        data.setCrop(crop);

        return environmentRepository.save(data);
    }

    @Override
    public List<EnvironmentData> getEnvironmentByCrop(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        return environmentRepository.findByCrop(crop);
    }
}