package com.cropmonitoring.repository;

import com.cropmonitoring.entity.EnvironmentData;
import com.cropmonitoring.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvironmentRepository extends JpaRepository<EnvironmentData, Long> {

    List<EnvironmentData> findByCrop(Crop crop);
}