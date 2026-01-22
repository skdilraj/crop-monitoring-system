package com.cropmonitoring.repository;


import com.cropmonitoring.entity.Crop;
import com.cropmonitoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByFarmer(User farmer);

}