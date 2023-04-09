package com.olmez.mya.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.model.Employee;
import com.olmez.mya.model.mock.MockEmployee;
import com.olmez.mya.repository.EmployeeRepository;
import com.olmez.mya.services.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;
    @Mock
    private EmployeeRepository empRepository;

    private Employee emp;
    private Employee emp2;

    @BeforeEach
    public void setup() {
        emp = new MockEmployee("Employee");
        emp2 = new MockEmployee("Employee2");
    }

    @Test
    void testGetEmployees() {
        // arrange
        when(empRepository.findAll()).thenReturn(List.of(emp, emp2));
        // act
        var employees = service.getAllEmployees();
        // assert
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0)).isEqualTo(emp);
        assertThat(employees.get(1)).isEqualTo(emp2);
    }

    @Test
    void testUpdateEmployee() {
        // arrange
        when(empRepository.getById(emp.getId())).thenReturn(emp);
        when(empRepository.save(any(Employee.class))).thenReturn(emp);
        var newEmp = new MockEmployee("New Employee");
        // act
        var updated = service.updateEmployee(emp.getId(), newEmp);
        // assert
        assertThat(updated).isEqualTo(emp.getId());
    }

}
