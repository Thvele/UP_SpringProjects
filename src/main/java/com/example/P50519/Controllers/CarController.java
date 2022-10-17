package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/car")
    public String Car(Model model) {

        Iterable<Car> listCar = carRepository.findAll();
        model.addAttribute(("list_cars"), listCar);
        return ("car/index");
    }

    @GetMapping("/car/add")
    public String CarAddView() {
        return ("car/carADD");
    }

    @PostMapping("/car/add")
    public String CarAdd(Model model,
                         @RequestParam String carName,
                         @RequestParam String carColor,
                         @RequestParam char carKPPType,
                         @RequestParam Integer carCost,
                         @RequestParam String carDelivery) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Car car = new Car(carName, carColor, carKPPType, carCost, formatter.parse(carDelivery, new ParsePosition(0)));
        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/car/filterACCU")
    public String CarFilterACCU(Model model,
                     @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarName(carName);
        model.addAttribute("searchRes", car);
        return ("car/carFilter");
    }

    @GetMapping("/car/filter")
    public String CarFilter(Model model,
                            @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarNameContains(carName);
        model.addAttribute("searchRes", car);
        return ("car/carFilter");
    }
}
