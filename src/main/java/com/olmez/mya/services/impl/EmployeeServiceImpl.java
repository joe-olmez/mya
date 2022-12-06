package com.olmez.mya.services.impl;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<Employee> getEmployees() {
        return empRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addEmployee(Employee newEmployee) {
        if (newEmployee == null) {
            return false;
        }
        newEmployee = empRepository.save(newEmployee);
        return newEmployee.getId() != null;
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long empId) {
        if (empId == null) {
            return null;
        }
        return empRepository.getById(empId);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Long empId) {
        Employee existing = getEmployeeById(empId);
        if (existing == null) {
            return false;
        }
        empRepository.deleted(existing);
        log.info("Deleted! {}", existing);
        return true;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee givenEmployee) {
        Employee existing = getEmployeeById(id);
        if (existing == null) {
            return null;
        }

        existing.setName(givenEmployee.getName());
        existing.setEmail(givenEmployee.getEmail());
        empRepository.save(existing);
        log.info("Updated! {}", existing);
        return existing;
    }

}
