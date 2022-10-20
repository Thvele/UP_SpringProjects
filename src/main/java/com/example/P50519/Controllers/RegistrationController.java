package com.example.P50519.Controllers;

import com.example.P50519.Models.Employee;
import com.example.P50519.Models.Role;
import com.example.P50519.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/registration")
    public String reg(Employee employee) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(Employee employee,
                      Model model) {

        if(employeeRepository.findByUsername(employee.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        } // Проверяем есть ли запись с идентичным логином

        employee.setActive(true); // Активируем аккаунт
        employee.setRoles(Collections.singleton(Role.USER)); // Добавляеи роль

        employeeRepository.save(employee); // Сохраняем аккаунт

        return ("redirect:/login");
    }
}
