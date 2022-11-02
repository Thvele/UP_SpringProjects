package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.User;
import com.example.P50519.Repositories.CarRepository;
import com.example.P50519.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    @GetMapping("/tablesCar")
    public String carTables(){

        return ("tablesCar");
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        Iterable<Car> listCar = carRepository.findAll();

        User currUser = userRepository.findByLogin(principal.getName());

        ArrayList<Car> carArrayList = new ArrayList<>();
        for(Car car: currUser.getCars()){
            carArrayList.add(car);
        }

        Car cart_t = new Car("0", 0, BigDecimal.valueOf(1.0), 0, null, null, null, null, null, null, null, null);

        cart_t.setIdcar(Long.valueOf(0));

        if(carArrayList.isEmpty())
            carArrayList.add(cart_t);

        model.addAttribute("listCar", listCar);
        model.addAttribute("favcars", carArrayList);


        return ("index");
    }

    @GetMapping("search")
    public String carSearch(Model model, @RequestParam String search, Principal principal){

        Iterable<Car> listCar = carRepository.findByCarnameContains(search);

        User currUser = userRepository.findByLogin(principal.getName());

        ArrayList<Car> carArrayList = new ArrayList<>();
        for(Car car: currUser.getCars()){
            carArrayList.add(car);
        }

        Car cart_t = new Car("0", 0, BigDecimal.valueOf(1.0), 0, null, null, null, null, null, null, null, null);

        cart_t.setIdcar(Long.valueOf(0));

        if(carArrayList.isEmpty())
            carArrayList.add(cart_t);

        model.addAttribute("listCar", listCar);
        model.addAttribute("favcars", carArrayList);

        return ("carsSearch");
    }


    @PostMapping("/favEDT")
    public String favoriteEDT(@RequestParam Long id, @RequestParam String func, Principal principal){

        if(func.equals("Добавить")){
            User currUser = userRepository.findByLogin(principal.getName());
            ArrayList<Car> carArrayList = new ArrayList<>();
            for(Car car: currUser.getCars()){
                carArrayList.add(car);
            }

            carArrayList.add(carRepository.findById(id).orElseThrow());

            currUser.setCars(carArrayList);
            userRepository.save(currUser);
        }
        else{
            User currUser = userRepository.findByLogin(principal.getName());
            ArrayList<Car> carArrayList = new ArrayList<>();
            for(Car car: currUser.getCars()){
                carArrayList.add(car);
            }

            carArrayList.remove(carRepository.findById(id).orElseThrow());

            currUser.setCars(carArrayList);
            userRepository.save(currUser);
        }

        return("redirect:/fav");
    }

    @GetMapping("/fav")
    private String favorite(Principal principal, Model model) {

        User user = userRepository.findByLogin(principal.getName());
        model.addAttribute("user", user);
        System.out.println();
        return("favorite");
    }

    @PostMapping("/fav")
    private String facDEL(@RequestParam Long id, Principal principal){

        User user = userRepository.findByLogin(principal.getName());
        ArrayList<Car> carArrayList = new ArrayList<>();
        for(Car car: user.getCars()){
            carArrayList.add(car);
        }

        carArrayList.remove(carRepository.findById(id).orElseThrow());

        user.setCars(carArrayList);
        userRepository.save(user);

        return ("redirect:/fav");
    }
}
