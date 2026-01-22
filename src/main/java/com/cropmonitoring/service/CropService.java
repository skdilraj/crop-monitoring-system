package com.cropmonitoring.service;

import com.cropmonitoring.entity.Crop;

import java.util.List;

public interface CropService {

    Crop addCrop(Crop crop, Long farmerId);

    List<Crop> getCropsByFarmer(Long farmerId);
}
