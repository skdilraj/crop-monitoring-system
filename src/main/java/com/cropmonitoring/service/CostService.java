package com.cropmonitoring.service;


import com.cropmonitoring.entity.CropCost;

public interface CostService {

    CropCost addOrUpdateCost(CropCost cost, Long cropId);

    CropCost getCostByCrop(Long cropId);
}
