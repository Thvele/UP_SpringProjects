package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfuel;

    @NotBlank(message = "Поле «Тип топлива» не может быть пустым!")
    private String fuelname;

    @OneToMany(mappedBy = "idfuel", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public FuelType() {
    }


    public FuelType(String fuelname, Collection<Car> cars) {
        this.fuelname = fuelname;
        this.cars = cars;
    }

    public Long getIdfuel() {
        return idfuel;
    }

    public void setIdfuel(Long idfuel) {
        this.idfuel = idfuel;
    }

    public String getFuelname() {
        return fuelname;
    }

    public void setFuelname(String fuelname) {
        this.fuelname = fuelname;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
