package com.cropmonitoring.repository;

import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.SellingPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellingPriceRepository
        extends JpaRepository<SellingPrice, Long> {

    Optional<SellingPrice> findByCrop(Crop crop);
}
