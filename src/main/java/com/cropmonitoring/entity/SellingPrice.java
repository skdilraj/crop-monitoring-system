package com.cropmonitoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "selling_price")
public class SellingPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // price of 1 unit (kg / quintal / ton)
    @Column(nullable = false)
    private Double pricePerUnit;

    // total quantity sold
    @Column(nullable = false)
    private Double quantity;

    // pricePerUnit × quantity
    @Column(nullable = false)
    private Double totalSellingAmount;

    // totalSellingAmount − totalCost
    @Column(nullable = false)
    private Double profit;

    // one crop has one selling record
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", nullable = false, unique = true)
    private Crop crop;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalSellingAmount() {
        return totalSellingAmount;
    }

    public void setTotalSellingAmount(Double totalSellingAmount) {
        this.totalSellingAmount = totalSellingAmount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
