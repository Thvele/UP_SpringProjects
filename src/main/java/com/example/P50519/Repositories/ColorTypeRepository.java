package com.example.P50519.Repositories;

import com.example.P50519.Models.ColorTypes;
import org.springframework.data.repository.CrudRepository;

public interface ColorTypeRepository extends CrudRepository<ColorTypes, Long> {
    ColorTypes findByColorType(String ct);
}
