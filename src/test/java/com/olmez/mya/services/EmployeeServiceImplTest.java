package com.olmez.mya.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.model.Employee;
import com.olmez.mya.repositories.EmployeeRepository;
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
        emp = new Employee("First", "Last");
        emp.setId(1L);
        emp2 = new Employee("Employee", "Sec");
        emp2.setId(2L);
    }

    @Test
    void testGetEmployees() {
        // arrange
        when(empRepository.findAll()).thenReturn(List.of(emp, emp2));
        // act
        var employees = service.getEmployees();
        // assert
        assertThat(employees).isNotEmpty();
        assertThat(employees.get(0)).isEqualTo(emp);
        assertThat(employees.get(1)).isEqualTo(emp2);
    }

    @Test
    void testUpdateEmployee() {
        // arrange
        when(empRepository.getById(emp.getId())).thenReturn(emp);
        Employee newEmp = new Employee("New Name", "employee@email.com");
        // act
        var updated = service.updateEmployee(emp.getId(), newEmp);
        // assert
        assertThat(updated.getId()).isEqualTo(emp.getId());
        assertThat(updated.getName()).isEqualTo(newEmp.getName());
    }

}
