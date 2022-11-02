package com.example.P50519.Controllers;

import com.example.P50519.Models.Color;
import com.example.P50519.Models.ColorTypes;
import com.example.P50519.Repositories.ColorRepository;
import com.example.P50519.Repositories.ColorTypeRepository;
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
public class ColorController {

    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ColorTypeRepository colorTypeRepository;

    @GetMapping("/color")
    private String colorView(Model model, Principal principal){

        Iterable<Color> listObject = colorRepository.findAll();
        model.addAttribute("listObject", listObject);
        return ("carTablesEdit/color/tableView");
    }

    @GetMapping("/carTablesEdit/color/DEL/{id}")
    private String transmissionDEL(@PathVariable long id){

        colorRepository.deleteById(id);
        return ("redirect:/color");
    }

    @GetMapping("/carTablesEdit/color/EDT/{id}")
    private String transmissionEDT(@PathVariable long id, Model model) {

        Color color = colorRepository.findById(id).orElseThrow();
        Iterable<ColorTypes> colorTypes = colorTypeRepository.findAll();

        model.addAttribute("object", color);
        model.addAttribute("colorTypes", colorTypes);

        return ("carTablesEdit/color/EDT");
    }

    @PostMapping("/carTablesEdit/color/EDT/{id}")
    private String transmissionEDT(@PathVariable long id,
                                   @RequestParam String colormain,
                                   @RequestParam String colorsub,
                                   @RequestParam String colorType_) {

        Color color  = colorRepository.findById(id).orElseThrow();
        ColorTypes colorType = colorTypeRepository.findByColorType(colorType_);

        color.setColormain(colormain);
        color.setColorsub(colorsub);
        color.setColorType(colorType);
        colorRepository.save(color);

        return ("redirect:/color");
    }

    @GetMapping("/carTablesEdit/color/ADD")
    private String transmissionADD(Color color, Model model) {

        Iterable<ColorTypes> colorTypes = colorTypeRepository.findAll();
        model.addAttribute("colorTypes", colorTypes);

        return ("carTablesEdit/color/ADD");
    }

    @PostMapping("/carTablesEdit/color/ADD")
    private String transmissionADD(@Valid Color color, BindingResult result) {

        colorRepository.save(color);
        return ("redirect:/color");
    }
}
