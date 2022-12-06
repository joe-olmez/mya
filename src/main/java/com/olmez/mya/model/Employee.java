package com.olmez.mya.model;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "name")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseObject {

	private String name;
	private String email;

	@Override
	public String toString() {
		return name;
	}

}
