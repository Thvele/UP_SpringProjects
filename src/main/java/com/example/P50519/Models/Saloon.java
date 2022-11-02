package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Saloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsaloon;

    @NotBlank(message = "Поле «Наименование салона» не может быть пустым!")
    private String saloonname;

    @NotBlank(message = "Поле «Адрес салона» не может быть пустым!")
    private String saloonaddress;

    @ManyToMany
    @JoinTable (name = "car_saloon",
            joinColumns = @JoinColumn(name = "saloon_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;

    public Saloon() {
    }


    public Saloon(String saloonname, String saloonaddress, List<Car> cars) {
        this.saloonname = saloonname;
        this.saloonaddress = saloonaddress;
        this.cars = cars;
    }

    public Long getIdsaloon() {
        return idsaloon;
    }

    public void setIdsaloon(Long idsaloon) {
        this.idsaloon = idsaloon;
    }

    public String getSaloonname() {
        return saloonname;
    }

    public void setSaloonname(String saloonname) {
        this.saloonname = saloonname;
    }

    public String getSaloonaddress() {
        return saloonaddress;
    }

    public void setSaloonaddress(String saloonaddress) {
        this.saloonaddress = saloonaddress;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
