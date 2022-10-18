package com.example.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.xml.stream.XMLEventWriter;
import java.util.Date;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150, message = "Поле должно содержать от 1 до 150 символов")
    private String carName;

    @NotBlank(message = "Поле не должно быть пустым!")
    private String carColor;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 0, max = 1, message = "Нужно ввести м(механика) или а(автомат)")
    private String carKPPType;

    @NotNull(message = "Поле не моет быть пустым")
    @Max(value = 999999999, message = "Стоимость не может превышать больше 999 млн.")
    @Min(value = 1, message = "Стоимость не может быть меньше 1")
    //@Positive(message = "Стоимость должна быть положительной!")
    //@PositiveOrZero - Положительное или 0
    //@Negative - Отрицательное число
    //@NegativeOrZero - Отрицательное или 0
    //DecimalMax()
    //DecimalMin()
    private Integer carCost;

    //@NotBlank(message = "Поле не должно быть пустым!")
    //@Past(message = "Дата рождения не может быть в настоящем или будущем") //Прошлое
    //@PastOrPresent - Прошлое или настоящее
    //@Future - Будущее
    //@FutureOrPresent - Будущее или настроящее
    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Дата не соотвествует маске ввода")
    private String carDelivery;


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

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarKPPType() {
        return carKPPType;
    }

    public void setCarKPPType(String carKPPType) {
        this.carKPPType = carKPPType;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
    }

    public String getCarDelivery() {
        return carDelivery;
    }

    public void setCarDelivery(String carDelivery) {
        this.carDelivery = carDelivery;
    }

    public Car() { }

    public Car(String carName, String carColor, String carKPPType, Integer carCost, String carDelivery) {
        this.carName = carName;
        this.carColor = carColor;
        this.carKPPType = carKPPType;
        this.carCost = carCost;
        this.carDelivery = carDelivery;
    }
}
