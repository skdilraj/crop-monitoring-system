package com.cropmonitoring.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "crop_cost")
public class CropCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double seedCost;
    private Double fertilizerCost;
    private Double laborCost;
    private Double irrigationCost;
    private Double otherCost;

    private Double totalCost;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getSeedCost() { return seedCost; }
    public void setSeedCost(Double seedCost) { this.seedCost = seedCost; }

    public Double getFertilizerCost() { return fertilizerCost; }
    public void setFertilizerCost(Double fertilizerCost) { this.fertilizerCost = fertilizerCost; }

    public Double getLaborCost() { return laborCost; }
    public void setLaborCost(Double laborCost) { this.laborCost = laborCost; }

    public Double getIrrigationCost() { return irrigationCost; }
    public void setIrrigationCost(Double irrigationCost) { this.irrigationCost = irrigationCost; }

    public Double getOtherCost() { return otherCost; }
    public void setOtherCost(Double otherCost) { this.otherCost = otherCost; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }
}