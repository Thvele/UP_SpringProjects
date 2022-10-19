package com.example.P50519.Repositories;

import com.example.P50519.Models.CarColor;
import org.springframework.data.repository.CrudRepository;

public interface CarColorRepository extends CrudRepository<CarColor, Long> {
    CarColor findByMainColor(String carColor);
}
