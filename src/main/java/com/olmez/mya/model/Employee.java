package com.olmez.mya.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseObject {

	private String name;
	private String email;
	private Integer age;
	private String phone;
	private LocalDate hiredOn;

	public Employee(String name, String email) {
		this(name, email, null, null, null);
	}

	public Employee(String name, String email, Integer age, String phone, LocalDate hiredOn) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.hiredOn = hiredOn;
	}

	@Override
	public String toString() {
		return name;
	}

}
