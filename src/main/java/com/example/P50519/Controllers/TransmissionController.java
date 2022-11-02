package com.example.P50519.Controllers;

import com.example.P50519.Models.Transmission;
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
public class TransmissionController {
    @Autowired
    TransmissionRepository transmissionRepository;

    @GetMapping("/transmission")
    private String transmissionView(Model model, Principal principal){

        Iterable<Transmission> listTrans = transmissionRepository.findAll();
        model.addAttribute("listTrans", listTrans);
        return ("carTablesEdit/transmission/tableView");
    }

    @GetMapping("/carTablesEdit/transmission/DEL/{id}")
    private String transmissionDEL(@PathVariable long id){

        transmissionRepository.deleteById(id);
        return ("redirect:/transmission");
    }

    @GetMapping("/carTablesEdit/transmission/EDT/{id}")
    private String transmissionEDT(@PathVariable long id, Model model) {

        Transmission transmission = transmissionRepository.findById(id).orElseThrow();
        model.addAttribute("transmission", transmission);
        return ("carTablesEdit/transmission/EDT");
    }

    @PostMapping("/carTablesEdit/transmission/EDT/{id}")
    private String transmissionEDT(@PathVariable long id,
                                   @RequestParam String name) {

        Transmission transmission = transmissionRepository.findById(id).orElseThrow();
        transmission.setTransmissionname(name);
        transmissionRepository.save(transmission);

        return ("redirect:/transmission");
    }

    @GetMapping("/carTablesEdit/transmission/ADD")
    private String transmissionADD(Transmission transmission) {

        return ("carTablesEdit/transmission/ADD");
    }

    @PostMapping("/carTablesEdit/transmission/ADD")
    private String transmissionADD(@Valid Transmission transmission, BindingResult result) {

        transmissionRepository.save(transmission);
        return ("redirect:/transmission");
    }
}
