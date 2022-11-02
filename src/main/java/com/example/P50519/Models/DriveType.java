package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class DriveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddrive;

    @NotBlank(message = "Поле «Привод» не может быть пустым!")
    private String drivename;

    @OneToMany(mappedBy = "iddrive", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public DriveType() {
    }


    public DriveType(String drivename, Collection<Car> cars) {
        this.drivename = drivename;
        this.cars = cars;
    }

    public Long getIddrive() {
        return iddrive;
    }

    public void setIddrive(Long iddrive) {
        this.iddrive = iddrive;
    }

    public String getDrivename() {
        return drivename;
    }

    public void setDrivename(String drivename) {
        this.drivename = drivename;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
