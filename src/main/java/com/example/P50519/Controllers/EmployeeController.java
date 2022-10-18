package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.Employee;
import com.example.P50519.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("")
    public String Employee(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/index");
    }

    @GetMapping("/add")
    public String EmployeeAddView() {return ("employee/employeeADD");}

    @PostMapping("/add")
    public String EmployeeAdd(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam String middleName,
                              @RequestParam Integer passport,
                              @RequestParam String birthday) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Employee employee = new Employee(surname, name, middleName, passport, formatter.parse(birthday, new ParsePosition(0)));
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurname(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurnameContains(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/details/{id}")
    public String EmployeeDetails(Model model,
                             @PathVariable long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return ("/employee/employeeDetails");
    }

    @GetMapping("/delete/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }

    @GetMapping("/edit/{id}")
    public String EmployeeEdit(Model model,
                          @PathVariable long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return("/employee/employeeEDT");
    }

    @PostMapping("/edit/{id}")
    public String EmployeeEdit(@PathVariable long id,
                          @RequestParam String surname,
                          @RequestParam String name,
                          @RequestParam String middleName,
                          @RequestParam Integer passport,
                          @RequestParam String birthday) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setSurname(surname);
        employee.setName(name);
        employee.setMiddleName(middleName);
        employee.setPassport(passport);
        employee.setBirthday(formatter.parse(birthday, new ParsePosition(0)));

        employeeRepository.save(employee);

        return("redirect:/employee/details/" + employee.getId());
    }
}
