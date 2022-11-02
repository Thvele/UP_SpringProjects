package com.example.P50519.Controllers;

import com.example.P50519.Models.Saloon;
import com.example.P50519.Models.Transmission;
import com.example.P50519.Repositories.SaloonRepository;
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
public class SaloonController {

    @Autowired
    SaloonRepository saloonRepository;

    @GetMapping("/saloon")
    private String transmissionView(Model model, Principal principal){

        Iterable<Saloon> listSaloons = saloonRepository.findAll();
        model.addAttribute("listSaloons", listSaloons);
        return ("carTablesEdit/saloon/tableView");
    }

    @GetMapping("/carTablesEdit/saloon/DEL/{id}")
    private String transmissionDEL(@PathVariable long id){

        saloonRepository.deleteById(id);
        return ("redirect:/saloon");
    }

    @GetMapping("/carTablesEdit/saloon/EDT/{id}")
    private String transmissionEDT(@PathVariable long id, Model model) {

        Saloon saloon = saloonRepository.findById(id).orElseThrow();
        model.addAttribute("object", saloon);
        return ("carTablesEdit/saloon/EDT");
    }

    @PostMapping("/carTablesEdit/saloon/EDT/{id}")
    private String transmissionEDT(@PathVariable long id,
                                   @RequestParam String saloonname,
                                   @RequestParam String saloonaddress) {

        Saloon saloon = saloonRepository.findById(id).orElseThrow();
        saloon.setSaloonname(saloonname);
        saloon.setSaloonaddress(saloonaddress);
        saloonRepository.save(saloon);

        return ("redirect:/saloon");
    }

    @GetMapping("/carTablesEdit/saloon/ADD")
    private String transmissionADD(Saloon saloon) {

        return ("carTablesEdit/saloon/ADD");
    }

    @PostMapping("/carTablesEdit/saloon/ADD")
    private String transmissionADD(@Valid Saloon saloon, BindingResult result) {

        saloonRepository.save(saloon);
        return ("redirect:/saloon");
    }
}
