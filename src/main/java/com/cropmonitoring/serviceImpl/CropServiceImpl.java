package com.cropmonitoring.serviceImpl;


import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.User;
import com.cropmonitoring.repository.CropRepository;
import com.cropmonitoring.repository.UserRepository;
import com.cropmonitoring.service.CropService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;

    public CropServiceImpl(CropRepository cropRepository,
                           UserRepository userRepository) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Crop addCrop(Crop crop, Long farmerId) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        crop.setFarmer(farmer);
        return cropRepository.save(crop);
    }

    @Override
    public List<Crop> getCropsByFarmer(Long farmerId) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        return cropRepository.findByFarmer(farmer);
    }
}