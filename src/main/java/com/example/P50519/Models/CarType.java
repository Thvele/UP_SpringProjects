package com.example.P50519.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtype;

    @NotBlank(message = "Поле «Тип машины» не может быть пустым!")
    private String typename;

    @OneToMany(mappedBy = "idtype", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public CarType() {
    }


    public CarType(String typename, Collection<Car> cars) {
        this.typename = typename;
        this.cars = cars;
    }

    public Long getIdtype() {
        return idtype;
    }

    public void setIdtype(Long idtype) {
        this.idtype = idtype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
