package com.cropmonitoring.service;


import com.cropmonitoring.entity.EnvironmentData;

import java.util.List;

public interface EnvironmentService {

    EnvironmentData addEnvironmentData(EnvironmentData data, Long cropId);

    List<EnvironmentData> getEnvironmentByCrop(Long cropId);
}