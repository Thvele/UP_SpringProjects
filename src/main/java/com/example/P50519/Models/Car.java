package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcar;

    @NotBlank(message = "Поле «Наименование машины» не может быть пустым!")
    private String carname;

    @NotNull(message = "Поле «Стоимость» не моет быть пустым")
    @Positive
    @Max(value = 999999999, message = "Стоимость не может превышать 999 млн.")
    @Min(value = 1, message = "Стоимость не может быть меньше 1")
    private Integer carcost;

    @NotNull(message = "Поле «Ёмкость двигателя» не моет быть пустым")
    @Positive
    @DecimalMax(value = "99.99")
    @DecimalMin(value = "1.00")
    @Digits(integer = 2, fraction = 2)
    private BigDecimal carengineSize;

    @NotNull(message = "Поле «Кол-во лошадиных сил» не моет быть пустым")
    @Positive
    @Min(value = 1, message = "Кол-во л.с. не может быть меньше 1")
    @Max(value = 9999, message = "Кол-во л.с. не может превышать 9999")
    private Integer carengineHP;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarType idtype;

    @ManyToOne(cascade = CascadeType.ALL)
    private DriveType iddrive;

    @ManyToOne(cascade = CascadeType.ALL)
    private Color idcolor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Transmission idtransmission;

    @ManyToOne(cascade = CascadeType.ALL)
    private FuelType idfuel;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarPhoto idcarPhoto;

    @ManyToMany
    @JoinTable(name = "car_saloon",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "saloon_id"))
    private List<Saloon> saloons;

    @ManyToMany
    @JoinTable (name = "favorite",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


    public Car() {
    }


    public Car(String carname, Integer carcost, BigDecimal carengineSize, Integer carengineHP, CarType idtype, DriveType iddrive, Color idcolor, Transmission idtransmission, FuelType idfuel, CarPhoto idcarPhoto, List<Saloon> saloons, List<User> users) {
        this.carname = carname;
        this.carcost = carcost;
        this.carengineSize = carengineSize;
        this.carengineHP = carengineHP;
        this.idtype = idtype;
        this.iddrive = iddrive;
        this.idcolor = idcolor;
        this.idtransmission = idtransmission;
        this.idfuel = idfuel;
        this.idcarPhoto = idcarPhoto;
        this.saloons = saloons;
        this.users = users;
    }

    public Long getIdcar() {
        return idcar;
    }

    public void setIdcar(Long idcar) {
        this.idcar = idcar;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public Integer getCarcost() {
        return carcost;
    }

    public void setCarcost(Integer carcost) {
        this.carcost = carcost;
    }

    public BigDecimal getCarengineSize() {
        return carengineSize;
    }

    public void setCarengineSize(BigDecimal carengineSize) {
        this.carengineSize = carengineSize;
    }

    public Integer getCarengineHP() {
        return carengineHP;
    }

    public void setCarengineHP(Integer carengineHP) {
        this.carengineHP = carengineHP;
    }

    public CarType getIdtype() {
        return idtype;
    }

    public void setIdtype(CarType idtype) {
        this.idtype = idtype;
    }

    public DriveType getIddrive() {
        return iddrive;
    }

    public void setIddrive(DriveType iddrive) {
        this.iddrive = iddrive;
    }

    public Color getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(Color idcolor) {
        this.idcolor = idcolor;
    }

    public Transmission getIdtransmission() {
        return idtransmission;
    }

    public void setIdtransmission(Transmission idtransmission) {
        this.idtransmission = idtransmission;
    }

    public FuelType getIdfuel() {
        return idfuel;
    }

    public void setIdfuel(FuelType idfuel) {
        this.idfuel = idfuel;
    }

    public CarPhoto getIdcarPhoto() {
        return idcarPhoto;
    }

    public void setIdcarPhoto(CarPhoto idcarPhoto) {
        this.idcarPhoto = idcarPhoto;
    }

    public List<Saloon> getSaloons() {
        return saloons;
    }

    public void setSaloons(List<Saloon> saloons) {
        this.saloons = saloons;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
