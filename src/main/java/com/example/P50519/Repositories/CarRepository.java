package com.example.P50519.Repositories;

import com.example.P50519.Models.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    Iterable<Car> findByCarnameContains(String c);
}
