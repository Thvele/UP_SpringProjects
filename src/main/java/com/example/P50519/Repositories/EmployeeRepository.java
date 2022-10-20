package com.example.P50519.Repositories;

import com.example.P50519.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findBySurname(String surname);
    public List<Employee> findBySurnameContains(String surname);
    Employee findByUsername(String username);
}
