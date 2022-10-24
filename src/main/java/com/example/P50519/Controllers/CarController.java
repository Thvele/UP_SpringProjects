package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/car")
@PreAuthorize("hasAnyAuthority('USER')")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("")
    public String Car(Model model) {

        Iterable<Car> listCar = carRepository.findAll();
        model.addAttribute(("list_cars"), listCar);
        return ("car/index");
    }

    @GetMapping("/add")
    public String CarAddView(Car car) {
        return ("car/carADD");
    }

    @PostMapping("/add")
    public String CarAdd(@Valid Car car,
                         BindingResult result) { //BindingResult - Передаёт ошибки на страницу

        if(result.hasErrors())
            return ("car/carADD");

        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/filterACCU")
    public String CarFilterACCU(Model model,
                     @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarName(carName); //Добавляем все записи содержащие заданное значение в список
        model.addAttribute("searchRes", car); //Передаём в модель список
        return ("car/carFilter"); //Отображаем страницу поиска
    }

    @GetMapping("/filter")
    public String CarFilter(Model model,
                            @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarNameContains(carName); //Добавляем все записи содержащие заданное значение в список
        model.addAttribute("searchRes", car); //Передаём в модель список
        return ("car/carFilter"); //Отображаем страницу поиска
    }

    @GetMapping("/details/{id}")
    public String CarDetails(Model model,
                             @PathVariable long id) {

        Car car = carRepository.findById(id).orElseThrow(); //Ищем запись по ID
        model.addAttribute("car", car); //Передаём в модель запись
        return ("/car/carDetails"); //Отображаем страницу деталей
    }

    @GetMapping("/carDEL/{id}")
    public String CarDelete(@PathVariable long id) {

        carRepository.deleteById(id); //Удаление записи по ID
        return("redirect:/car"); //Перенаправление на страницу машин
    }

    @GetMapping("/edit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id,
                          Car car_) {

        Car car = carRepository.findById(id).orElseThrow(); //Поиск записи по ID
        model.addAttribute("car", car); //Передаём в модель запись
        return("/car/carEDT"); //Отображение страницы редактирования
    }

    @PostMapping("/edit/{id}")
    public String CarEdit(@Valid Car car,
                          BindingResult result) {

        if(result.hasErrors())
            return ("car/carEDT");

        carRepository.save(car); //Сохранение изменений

        return("redirect:/car/details/" + car.getId()); //Перенаправление на страницу деталей
    }
}
