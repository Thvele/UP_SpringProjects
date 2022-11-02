package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtransmission;

    @NotBlank(message = "Поле «Коробка передач» не может быть пустым!")
    private String transmissionname;

    @OneToMany(mappedBy = "idtransmission", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public Transmission() {
    }


    public Transmission(String transmissionname, Collection<Car> cars) {
        this.transmissionname = transmissionname;
        this.cars = cars;
    }

    public Long getIdtransmission() {
        return idtransmission;
    }

    public void setIdtransmission(Long idtransmission) {
        this.idtransmission = idtransmission;
    }

    public String getTransmissionname() {
        return transmissionname;
    }

    public void setTransmissionname(String transmissionname) {
        this.transmissionname = transmissionname;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
