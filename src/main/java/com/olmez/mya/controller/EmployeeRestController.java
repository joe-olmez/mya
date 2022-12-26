package com.olmez.mya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.Employee;
import com.olmez.mya.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/emp")
@AllArgsConstructor
public class EmployeeRestController {

    private final EmployeeService empService;

    // CREATE = POST
    @PostMapping("/add")
    public boolean addEmployee(@RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }

    // READ = GET
    @GetMapping("/all")
    public List<Employee> getEmployees() {
        return empService.getEmployees();
    }

    // UPDATE = PUT
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee model) {
        return empService.updateEmployee(id, model);
    }

    // DELETE = DELETE
    @DeleteMapping("/delete/{id}")
    public boolean deleteEmployee(@PathVariable("id") Long id) {
        return empService.deleteEmployee(id);
    }

    //
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return empService.getEmployeeById(id);
    }

}