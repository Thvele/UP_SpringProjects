package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcolor;

    @NotBlank(message = "Поле «Основной цвет» не может быть пустым!")
    private String colormain;

    @NotBlank(message = "Поле «Дополнительный цвет» не может быть пустым!")
    private String colorsub;

    @ManyToOne(cascade = CascadeType.ALL)
    private ColorTypes colorType;

    @OneToMany(mappedBy = "idcolor", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public Color() {
    }


    public Color(String colormain, String colorsub, ColorTypes ColorTypes, Collection<Car> cars) {
        this.colormain = colormain;
        this.colorsub = colorsub;
        this.colorType = ColorTypes;
        this.cars = cars;
    }

    public Long getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(Long idcolor) {
        this.idcolor = idcolor;
    }

    public String getColormain() {
        return colormain;
    }

    public void setColormain(String colormain) {
        this.colormain = colormain;
    }

    public String getColorsub() {
        return colorsub;
    }

    public void setColorsub(String colorsub) {
        this.colorsub = colorsub;
    }

    public ColorTypes getColorType() {
        return colorType;
    }

    public void setColorType(ColorTypes ColorTypes) {
        this.colorType = ColorTypes;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
