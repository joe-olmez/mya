package com.olmez.mya.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.olmez.mya.MyaTestApplication;
import com.olmez.mya.model.Employee;
import com.olmez.mya.utility.SourceUtils;

@SpringBootTest(classes = MyaTestApplication.class)
@ActiveProfiles(SourceUtils.TEST_PROFILE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@BeforeEach
	void clean() {
		repository.deleteAll();
	}

	@Test
	void testFindByName() {
		// arrange
		var emp = new Employee("Emp1name", "emp@email.com");
		emp = repository.save(emp);
		var emp2 = new Employee("Emp2name", "emp2@email.com");
		emp2 = repository.save(emp2);

		// act
		var list = repository.findByName(emp.getName());

		// assert
		assertThat(list).hasSize(1).contains(emp);
	}

}
