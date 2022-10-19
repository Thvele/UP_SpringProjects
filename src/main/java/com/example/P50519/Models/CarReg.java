package com.example.P50519.Models;

import javax.persistence.*;

@Entity
@Table(name = "carreg")
public class CarReg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sign;

    private String VIN;

    @OneToOne(mappedBy = "registration")
    private Car car;

    public CarReg() {
    }

    public CarReg(String sign, String VIN) {
        this.sign = sign;
        this.VIN = VIN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}