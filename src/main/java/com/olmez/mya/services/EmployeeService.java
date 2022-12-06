package com.olmez.mya.services;

import java.util.List;

import com.olmez.mya.model.Employee;

public interface EmployeeService {

    List<Employee> getEmployees();

    boolean addEmployee(Employee newEmployee);

    Employee getEmployeeById(Long empId);

    boolean deleteEmployee(Long empId);

    Employee updateEmployee(Long existingEmpId, Employee givenEmployee);

}
