package com.example.P50519.Controllers;

import com.example.P50519.Models.Employee;
import com.example.P50519.Models.Role;
import com.example.P50519.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
@PreAuthorize("hasAnyAuthority('HRdep')")
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
    public String EmployeeAddView(Employee employee) {return ("employee/employeeADD");}

    @PostMapping("/add")
    public String EmployeeAdd(@Valid Employee employee,
                              BindingResult result) {
        if(result.hasErrors())
            return ("employee/employeeADD");

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
    public String EmployeeEdit(@Valid Employee employee,
                               BindingResult result) {

        if(result.hasErrors())
            return ("employee/employeeEDT");

        employeeRepository.save(employee);

        return("redirect:/employee/details/" + employee.getId());
    }

    @GetMapping("/editR/{id}")
    public String EmployeeRoleEdit(Model model,
                               @PathVariable long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        model.addAttribute("listRoles", Role.values());
        return("/employee/emoloyeeRoleEDIT");
    }

    @PostMapping("/editR/{id}")
    public String EmployeeRoleEdit(@RequestParam long id,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String middleName,
                                   @RequestParam String[] roles) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setMiddleName(middleName);
        employee.getRoles().clear();

        for(String role: roles){
            employee.getRoles().add(Role.valueOf(role));
        }

        employeeRepository.save(employee);

        return("redirect:/employee");
    }
}
