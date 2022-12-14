package com.olmez.mya.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.olmez.mya.model.Employee;

public interface EmployeeRepository extends BaseObjectRepository<Employee> {

    @Query("SELECT u FROM Employee u WHERE u.name = ?1 AND u.deleted = false")
    List<Employee> findByName(String name);

}
