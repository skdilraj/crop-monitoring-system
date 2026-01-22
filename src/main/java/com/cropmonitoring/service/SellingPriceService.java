package com.cropmonitoring.service;

import com.cropmonitoring.entity.SellingPrice;

public interface SellingPriceService {

    // CREATE or UPDATE selling price + profit
    SellingPrice addOrUpdateSellingPrice(SellingPrice sellingPrice, Long cropId);

    // GET selling details
    SellingPrice getSellingDetails(Long cropId);

    // 💰 GET PROFIT ONLY (NEW)
    Double calculateProfit(Long cropId);
}
