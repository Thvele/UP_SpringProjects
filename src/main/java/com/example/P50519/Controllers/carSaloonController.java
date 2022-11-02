package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.Saloon;
import com.example.P50519.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class carSaloonController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    SaloonRepository saloonRepository;

    @GetMapping("/carSaloon")
    private String carView(Model model){

        Iterable<Car> listObject = carRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/carSaloon/tableView");
    }

    @GetMapping("/carTablesEdit/carSaloon/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id, Model model){

        Car car = carRepository.findById(id).orElseThrow();

        model.addAttribute("object", car);
        return ("carTablesEdit/carSaloon/DEL");
    }

    @PostMapping("/carTablesEdit/carSaloon/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id,
                               @RequestParam String id_saloon){

        Car car = carRepository.findById(id).orElseThrow();
        List<Saloon> saloonList = car.getSaloons();
        saloonList.remove(saloonRepository.findById(Long.valueOf(id_saloon.split("\\|")[0])).orElseThrow());

        car.setSaloons(saloonList);
        carRepository.save(car);
        return ("redirect:/carSaloon");
    }

    @GetMapping("/carTablesEdit/carSaloon/ADD")
    private String fuelTypeADD(Model model) {

        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("saloons", saloonRepository.findAll());

        return ("carTablesEdit/carSaloon/ADD");
    }

    @PostMapping("/carTablesEdit/carSaloon/ADD")
    private String fuelTypeADD(@RequestParam String id_car, @RequestParam String id_saloon) {

        Car car = carRepository.findById(Long.valueOf(id_car.split("\\|")[0])).orElseThrow();
        List<Saloon> saloonList = car.getSaloons();

        saloonList.add(saloonRepository.findById(Long.valueOf(id_saloon.split("\\|")[0])).orElseThrow());

        car.setSaloons(saloonList);
        carRepository.save(car);
        return ("redirect:/carSaloon");
    }
}
