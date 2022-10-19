package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.CarColor;
import com.example.P50519.Models.CarReg;
import com.example.P50519.Models.Saloon;
import com.example.P50519.Repositories.CarColorRepository;
import com.example.P50519.Repositories.CarRegRepository;
import com.example.P50519.Repositories.CarRepository;
import com.example.P50519.Repositories.SaloonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    SaloonRepository saloonRepository;
    @Autowired
    CarColorRepository carColorRepository;
    @Autowired
    CarRegRepository carRegRepository;

    @GetMapping("/car")
    public String Car(Model model) {

        Iterable<Car> listCar = carRepository.findAll();
        Iterable<Saloon> listSaloon = saloonRepository.findAll();
        Iterable<CarColor> listCarColor = carColorRepository.findAll();
        Iterable<CarReg> listCarReg = carRegRepository.findAll();



        model.addAttribute(("list_cars"), listCar);
        model.addAttribute(("list_saloons"), listSaloon);
        model.addAttribute(("list_carColors"), listCarColor);
        model.addAttribute(("list_carReg"), listCarReg);
        return ("car/index");
    }

    @GetMapping("/car/carSaloonADD")
    public String carSaloonADD(Model model) {

        Iterable<Car> listCar = carRepository.findAll();
        Iterable<Saloon> listSaloon = saloonRepository.findAll();

        model.addAttribute(("cars"), listCar);
        model.addAttribute(("saloons"), listSaloon);
        return ("car/carSaloonADD");
    }

    @PostMapping("/car/carSaloonADD")
    public String carSaloonADD(@RequestParam String car_name,
                           @RequestParam String saloon_name) {

        Car car = carRepository.findById(Long.valueOf(car_name.split("\\|")[0])).orElseThrow();
        Saloon saloon = saloonRepository.findById(Long.valueOf(saloon_name.split("\\|")[0])).orElseThrow();

        List<Saloon> saloonsList = car.getSaloons();
        saloonsList.add(saloon);

        car.setSaloons(saloonsList);

        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/car/add")
    public String CarAdd(Model model) {

        Iterable<CarColor> carColors = carColorRepository.findAll();
        Iterable<CarReg> carRegs = carRegRepository.findAll();
        ArrayList<CarReg> carRegArrayList = new ArrayList<>();

        for(CarReg cr: carRegs) {
            if(cr.getCar() == null) {
                carRegArrayList.add(cr);
            }
        }

        model.addAttribute("colors", carColors);
        model.addAttribute("regs", carRegArrayList);

        return ("car/carADD");
    }

    @PostMapping("/car/add")
    public String CarAdd(@RequestParam String carName,
                         @RequestParam Integer carCost,
                         @RequestParam String carColor,
                         @RequestParam String carReg) {

        CarColor carColor1 = carColorRepository.findByMainColor(carColor.split("\\|")[0]);
        CarReg carReg1 = carRegRepository.findBySign(carReg);

        Car car = new Car(carName, carCost, carReg1, carColor1);
        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/car/carColorADD")
    public String colorADD() {

        return ("car/carColorADD");
    }

    @PostMapping("/car/carColorADD")
    public String colorADD(@RequestParam String main,
                           @RequestParam String sub) {

        CarColor carColor = new CarColor(main, sub);
        carColorRepository.save(carColor);
        return ("redirect:/car");
    }

    @GetMapping("/car/carRegADD")
    public String regADD() {

        return ("car/carRegADD");
    }

    @PostMapping("/car/carRegADD")
    public String regADD(@RequestParam String sign,
                           @RequestParam String VIN) {

        CarReg carReg = new CarReg(sign, VIN);
        carRegRepository.save(carReg);
        return ("redirect:/car");
    }

    @GetMapping("/car/saloonADD")
    public String saloonADD() {

        return ("car/saloonADD");
    }

    @PostMapping("/car/saloonADD")
    public String saloonADD(@RequestParam String address,
                         @RequestParam String name) {

        Saloon saloon = new Saloon(address, name);
        saloonRepository.save(saloon);
        return ("redirect:/car");
    }
}
