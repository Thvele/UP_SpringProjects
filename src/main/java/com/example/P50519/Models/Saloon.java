package com.example.P50519.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Saloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String name;

    @ManyToMany
    @JoinTable (name = "cars_saloon",
                joinColumns = @JoinColumn(name = "saloon_id"),
                inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;

    public Saloon() {
    }

    public Saloon(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
