package com.example.P50519.Repositories;

import com.example.P50519.Models.Saloon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaloonRepository extends CrudRepository<Saloon, Long> {
    Saloon findByName(String name);
}
