package com.olmez.mya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.mya.model.Employee;
import com.olmez.mya.repositories.EmployeeRepository;
import com.olmez.mya.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository empRepository;

    @Override
    public Long addEmployee(Employee emp) {
        if (emp == null) {
            return null;
        }
        emp = empRepository.save(emp);
        return emp.getId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return (empId != null) ? empRepository.getById(empId) : null;
    }

    @Override
    public Long updateEmployee(Employee givenEmp) {
        if ((givenEmp == null) || (givenEmp.getId() == null)) {
            return null;
        }
        Employee existing = getEmployeeById(givenEmp.getId());
        if (existing == null) {
            return null;
        }
        return update(existing, givenEmp).getId();
    }

    @Override
    public Long updateEmployee(Long id, Employee givenEmp) {
        if ((id == null) || (givenEmp == null)) {
            return null;
        }
        Employee existing = getEmployeeById(id);
        if (existing == null) {
            return null;
        }
        return update(existing, givenEmp).getId();
    }

    private Employee update(Employee existing, Employee given) {
        existing.setName(given.getName());
        existing.setEmail(given.getEmail());
        existing.setDob(given.getDob());
        existing.setSalaried(given.isSalaried());
        existing = empRepository.save(existing);
        log.info("Updated! {}", existing);
        return existing;
    }

    @Override
    public boolean deleteEmployeeById(Long empId) {
        Employee existing = getEmployeeById(empId);
        if (existing == null) {
            return false;
        }
        empRepository.deleted(existing);
        return true;
    }

}
