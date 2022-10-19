package com.example.P50519.Repositories;

import com.example.P50519.Models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByCarName(String name);
    public List<Car> findByCarNameContains(String name);
}
