package com.olmez.mya.model;

import java.util.Objects;

import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.utility.StringUtility;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseObject {

	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String username;
	private @NonNull String email;
	private UserType userType = UserType.REGULAR;
	private String passwordHash;

	public User(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String email) {
		this(firstName, lastName, username, email, UserType.REGULAR);
	}

	public User(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String email,
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
