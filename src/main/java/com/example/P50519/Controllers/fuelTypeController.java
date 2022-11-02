package com.example.P50519.Controllers;

import com.example.P50519.Models.FuelType;
import com.example.P50519.Models.Transmission;
import com.example.P50519.Repositories.FuelTypeRepository;
import com.example.P50519.Repositories.TransmissionRepository;
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
public class fuelTypeController {

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    @GetMapping("/fuelType")
    private String fuelTypeView(Model model, Principal principal){

        Iterable<FuelType> listObject = fuelTypeRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/fuelType/tableView");
    }

    @GetMapping("/carTablesEdit/fuelType/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id){

        fuelTypeRepository.deleteById(id);
        return ("redirect:/fuelType");
    }

    @GetMapping("/carTablesEdit/fuelType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id, Model model) {

        FuelType fuelType = fuelTypeRepository.findById(id).orElseThrow();
        model.addAttribute("object", fuelType);
        return ("carTablesEdit/fuelType/EDT");
    }

    @PostMapping("/carTablesEdit/fuelType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id,
                                   @RequestParam String name) {

        FuelType fuelType = fuelTypeRepository.findById(id).orElseThrow();
        fuelType.setFuelname(name);
        fuelTypeRepository.save(fuelType);

        return ("redirect:/fuelType");
    }

    @GetMapping("/carTablesEdit/fuelType/ADD")
    private String fuelTypeADD(FuelType fuelType) {

        return ("carTablesEdit/fuelType/ADD");
    }

    @PostMapping("/carTablesEdit/fuelType/ADD")
    private String fuelTypeADD(@Valid FuelType fuelType, BindingResult result) {

        fuelTypeRepository.save(fuelType);
        return ("redirect:/fuelType");
    }
}
