package com.olmez.mya.model;

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

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return name;
	}

}
