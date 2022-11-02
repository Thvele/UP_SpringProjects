package com.example.P50519.Controllers;

import com.example.P50519.Models.CarPhoto;
import com.example.P50519.Repositories.CarPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class carPhotoController {

    @Autowired
    CarPhotoRepository carPhotoRepository;

    @GetMapping("/carPhoto")
    private String carPhotoView(Model model) {

        Iterable<CarPhoto> carPhotos = carPhotoRepository.findAll();
        model.addAttribute("listObject", carPhotos);
        return ("/carTablesEdit/carPhoto/tableView");
    }

    @GetMapping("/carTablesEdit/carPhoto/DEL/{id}")
    private String carPhotoDEL(@PathVariable long id){

        carPhotoRepository.deleteById(id);
        return ("redirect:/carPhoto");
    }

    @GetMapping("/carTablesEdit/carPhoto/ADD")
    private String carPhotoADD(CarPhoto carPhoto, Model model) {

        return ("carTablesEdit/carPhoto/ADD");
    }

    @PostMapping("/carTablesEdit/carPhoto/ADD")
    private String carPhotoADD(CarPhoto carPhoto) {

        carPhotoRepository.save(carPhoto);
        return ("redirect:/carPhoto");
    }
}
