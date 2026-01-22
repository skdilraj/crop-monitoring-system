package com.cropmonitoring.repository;


import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.CropCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostRepository extends JpaRepository<CropCost, Long> {

    Optional<CropCost> findByCrop(Crop crop);
}
