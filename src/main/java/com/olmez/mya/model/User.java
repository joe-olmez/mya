package com.olmez.mya.model;

import java.util.Objects;

import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.utility.StringUtility;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Required to solve the conflict "user" in h2 db
@Data
@NoArgsConstructor
public class User extends BaseObject {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private UserType userType = UserType.REGULAR;
	private String passwordHash;

	public User(String firstName, String lastName, String username, String email) {
		this(firstName, lastName, username, email, UserType.REGULAR);
	}

	public User(String firstName, String lastName, String username, String email,
			UserType userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.userType = (userType != null) ? userType : UserType.REGULAR;
	}

	public String getName() {
		return StringUtility.isEmpty(firstName) ? username : firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isAdmin() {
		return userType == UserType.ADMIN;
	}

	public String getRole() {
		return userType.getRole();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email);
	}

}
