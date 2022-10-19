package com.example.P50519.Repositories;

import com.example.P50519.Models.CarReg;
import org.springframework.data.repository.CrudRepository;

public interface CarRegRepository extends CrudRepository<CarReg, Long> {
    CarReg findBySign(String sign);
}
