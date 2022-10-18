package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/car")
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
    public String CarAddView() {
        return ("car/carADD");
    }

    @PostMapping("/add")
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

    @GetMapping("/filterACCU")
    public String CarFilterACCU(Model model,
                     @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarName(carName);
        model.addAttribute("searchRes", car);
        return ("car/carFilter");
    }

    @GetMapping("/filter")
    public String CarFilter(Model model,
                            @RequestParam(name = "search") String carName) {

        List<Car> car = carRepository.findByCarNameContains(carName);
        model.addAttribute("searchRes", car);
        return ("car/carFilter");
    }

    @GetMapping("/details/{id}")
    public String CarDetails(Model model,
                             @PathVariable long id) {

        Optional<Car> car = carRepository.findById(id);
        ArrayList<Car> result = new ArrayList<>();

        car.ifPresent(result::add);
        model.addAttribute("car", result);
        return ("/car/carDetails");
    }

    @GetMapping("/carDEL/{id}")
    public String CarDelete(@PathVariable long id) {

        carRepository.deleteById(id);
        return("redirect:/car");
    }

    @GetMapping("/edit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id) {

        Car car = carRepository.findById(id).orElseThrow();
        model.addAttribute("car", car);
        return("/car/carEDT");
    }

    @PostMapping("/edit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id,
                          @RequestParam String carName,
                          @RequestParam String carColor,
                          @RequestParam char carKPPType,
                          @RequestParam Integer carCost,
                          @RequestParam String carDelivery) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Car car = carRepository.findById(id).orElseThrow();
        car.setCarName(carName);
        car.setCarColor(carColor);
        car.setCarKPPType(carKPPType);
        car.setCarCost(carCost);
        car.setCarDelivery(formatter.parse(carDelivery, new ParsePosition(0)));

        carRepository.save(car);

        return("redirect:/car/details/" + car.getId());
    }
}
