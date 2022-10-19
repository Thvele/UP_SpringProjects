package com.example.P50519.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carName;

    private Integer carCost;

    @OneToOne(cascade = CascadeType.ALL) //Создание связи один к одному с каскадным удалением для таблицы регистраций
    @JoinColumn(name = "reg_id") //Указываем столбец как внешний ключ
    private CarReg registration;

    @ManyToOne(cascade = CascadeType.ALL) //Создание связи многие к одному с каскадным удалением для таблицы цветов
    private CarColor carColor;

    @ManyToMany //Создание связи многие ко многим для таблицы автосалонов
    @JoinTable(name = "cars_saloon", // Создаём таблицу с заданным именем
               joinColumns = @JoinColumn(name = "car_id"), // Создём столбец отвечающий за внешний ключ из этой сущности
               inverseJoinColumns = @JoinColumn(name = "saloon_id")) // Создём столбец отвечающий за внешний ключ из другой сущности
    private List<Saloon> saloons;

    public Car() {
    }

    public Car(String carName, Integer carCost, CarReg registration, CarColor carColor) {
        this.carName = carName;
        this.carCost = carCost;
        this.registration = registration;
        this.carColor = carColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
    }

    public CarReg getRegistration() {
        return registration;
    }

    public void setRegistration(CarReg registration) {
        this.registration = registration;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public List<Saloon> getSaloons() {
        return saloons;
    }

    public void setSaloons(List<Saloon> saloons) {
        this.saloons = saloons;
    }
}