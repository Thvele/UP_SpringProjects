package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class CarPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idphoto;

    @NotBlank(message = "Описание не дложно быть пустым")
    private String description;

    @NotBlank(message = "Ссылка на фото не должна быть пустой")
    private String photolink;

    @OneToMany(mappedBy = "idcarPhoto", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    public CarPhoto() {
    }

    public CarPhoto(String description, String photolink, Collection<Car> cars) {
        this.description = description;
        this.photolink = photolink;
        this.cars = cars;
    }

    public Long getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(Long idphoto) {
        this.idphoto = idphoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotolink() {
        return photolink;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
