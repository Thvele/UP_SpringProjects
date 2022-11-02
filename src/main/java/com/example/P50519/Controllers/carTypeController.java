package com.example.P50519.Controllers;

import com.example.P50519.Models.CarType;
import com.example.P50519.Models.DriveType;
import com.example.P50519.Repositories.CarTypeRepository;
import com.example.P50519.Repositories.DriveTypeRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN','REDACTOR')")
public class carTypeController {

    @Autowired
    CarTypeRepository carTypeRepository;

    @GetMapping("/carType")
    private String fuelTypeView(Model model, Principal principal){

        Iterable<CarType> listObject = carTypeRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/carType/tableView");
    }

    @GetMapping("/carTablesEdit/carType/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id){

        carTypeRepository.deleteById(id);
        return ("redirect:/carType");
    }

    @GetMapping("/carTablesEdit/carType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id, Model model) {

        CarType carType = carTypeRepository.findById(id).orElseThrow();
        model.addAttribute("object", carType);
        return ("carTablesEdit/carType/EDT");
    }

    @PostMapping("/carTablesEdit/carType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id,
                               @RequestParam String name) {

        CarType carType = carTypeRepository.findById(id).orElseThrow();
        carType.setTypename(name);
        carTypeRepository.save(carType);

        return ("redirect:/carType");
    }

    @GetMapping("/carTablesEdit/carType/ADD")
    private String fuelTypeADD(CarType carType) {

        return ("carTablesEdit/carType/ADD");
    }

    @PostMapping("/carTablesEdit/carType/ADD")
    private String fuelTypeADD(@Valid CarType carType, BindingResult result) {

        carTypeRepository.save(carType);
        return ("redirect:/carType");
    }
}
