package com.example.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carName;
    private String carColor;
    private char carKPPType;
    private Integer carCost;
    private Date carDelivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public char getCarKPPType() {
        return carKPPType;
    }

    public void setCarKPPType(char carKPPType) {
        this.carKPPType = carKPPType;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
    }

    public Date getCarDelivery() {
        return carDelivery;
    }

    public void setCarDelivery(Date carDelivery) {
        this.carDelivery = carDelivery;
    }

    public Car() { }

    public Car(String carName, String carColor, char carKPPType, Integer carCost, Date carDelivery) {
        this.carName = carName;
        this.carColor = carColor;
        this.carKPPType = carKPPType;
        this.carCost = carCost;
        this.carDelivery = carDelivery;
    }
}
