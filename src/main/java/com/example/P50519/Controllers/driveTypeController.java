package com.example.P50519.Controllers;

import com.example.P50519.Models.DriveType;
import com.example.P50519.Models.FuelType;
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
public class driveTypeController {

    @Autowired
    DriveTypeRepository driveTypeRepository;

    @GetMapping("/driveType")
    private String fuelTypeView(Model model, Principal principal){

        Iterable<DriveType> listObject = driveTypeRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/driveType/tableView");
    }

    @GetMapping("/carTablesEdit/driveType/DEL/{id}")
    private String fuelTypeDEL(@PathVariable long id){

        driveTypeRepository.deleteById(id);
        return ("redirect:/driveType");
    }

    @GetMapping("/carTablesEdit/driveType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id, Model model) {

        DriveType driveType = driveTypeRepository.findById(id).orElseThrow();
        model.addAttribute("object", driveType);
        return ("carTablesEdit/driveType/EDT");
    }

    @PostMapping("/carTablesEdit/driveType/EDT/{id}")
    private String fuelTypeEDT(@PathVariable long id,
                               @RequestParam String name) {

        DriveType driveType = driveTypeRepository.findById(id).orElseThrow();
        driveType.setDrivename(name);
        driveTypeRepository.save(driveType);

        return ("redirect:/driveType");
    }

    @GetMapping("/carTablesEdit/driveType/ADD")
    private String fuelTypeADD(DriveType driveType) {

        return ("carTablesEdit/driveType/ADD");
    }

    @PostMapping("/carTablesEdit/driveType/ADD")
    private String fuelTypeADD(@Valid DriveType driveType, BindingResult result) {

        driveTypeRepository.save(driveType);
        return ("redirect:/driveType");
    }
}
