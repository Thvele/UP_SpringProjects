package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.CarType;
import com.example.P50519.Models.FuelType;
import com.example.P50519.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class carController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    CarTypeRepository carTypeRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    DriveTypeRepository driveTypeRepository;
    @Autowired
    FuelTypeRepository fuelTypeRepository;
    @Autowired
    TransmissionRepository transmissionRepository;
    @Autowired
    CarPhotoRepository carPhotoRepository;

    @GetMapping("/car")
    private String carView(Model model){

        Iterable<Car> listObject = carRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/car/tableView");
    }

    @GetMapping("/carTablesEdit/car/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id){

        carRepository.deleteById(id);
        return ("redirect:/car");
    }

    @GetMapping("/carTablesEdit/car/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id, Model model) {

        model.addAttribute("carTypes", carTypeRepository.findAll());
        model.addAttribute("driveTypes", driveTypeRepository.findAll());
        model.addAttribute("colors", colorRepository.findAll());
        model.addAttribute("transmissions", transmissionRepository.findAll());
        model.addAttribute("fuelTypes", fuelTypeRepository.findAll());
        model.addAttribute("photos", carPhotoRepository.findAll());

        Car car = carRepository.findById(id).orElseThrow();
        model.addAttribute("object", car);
        return ("carTablesEdit/car/EDT");
    }

    @PostMapping("/carTablesEdit/car/EDT/{id}")
    private String fuelTypeEDT(Car car, @PathVariable long id) {

        car.setIdcar(id);
        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/carTablesEdit/car/ADD")
    private String fuelTypeADD(Car car, Model model) {

        model.addAttribute("carTypes", carTypeRepository.findAll());
        model.addAttribute("driveTypes", driveTypeRepository.findAll());
        model.addAttribute("colors", colorRepository.findAll());
        model.addAttribute("transmissions", transmissionRepository.findAll());
        model.addAttribute("fuelTypes", fuelTypeRepository.findAll());
        model.addAttribute("photos", carPhotoRepository.findAll());

        return ("carTablesEdit/car/ADD");
    }

    @PostMapping("/carTablesEdit/car/ADD")
    private String fuelTypeADD(Car car, BindingResult result) {

        carRepository.save(car);
        return ("redirect:/car");
    }
}
