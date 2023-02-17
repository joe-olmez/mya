package com.olmez.mya.model;

import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.utility.StringUtility;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
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
		if (this == obj)
			return true;

		if (!(obj instanceof User))
			return false;

		User other = (User) obj;
		return isValid(other.getUsername(), other.getEmail());
	}

	private boolean isValid(String oUsername, String oEmail) {
		return (StringUtility.isEmpty(username) && username.equalsIgnoreCase(oUsername)) ||
				(StringUtility.isEmpty(email) && email.equalsIgnoreCase(oEmail));
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = 11;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

}
