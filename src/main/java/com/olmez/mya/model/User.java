package com.olmez.mya.model;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.utility.DateUtility;
import com.olmez.mya.utility.StringUtility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "username")
@NoArgsConstructor
public class User extends BaseObject {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private UserType userType = UserType.REGULAR;
	private String passwordHash;
	private String timeZone;

	public User(String username, String firstName, String lastName) {
		this(username, firstName, lastName, username + "@noemail.com", UserType.REGULAR);
	}

	public User(String username, String firstName, String lastName, String email) {
		this(username, firstName, lastName, email, UserType.REGULAR);
	}

	public User(String username, String firstName, String lastName, String email, UserType userType) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.email = email;
	}

	public String getName() {
		return StringUtility.isEmpty(firstName) ? username : firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isAdmin() {
		return UserType.ADMIN.equals(getUserType());
	}

	public String getTimeZone() {
		return (timeZone == null) ? DateUtility.DEFAULT_ZONE_ID_KEY : timeZone;
	}

	public boolean isRegular() {
		return UserType.REGULAR.equals(getUserType());
	}

	public static boolean isValid(User user) {
		return !StringUtility.isEmpty(user.getUsername()) && !StringUtility.isEmpty(user.getFirstName())
				&& !StringUtility.isEmpty(user.getLastName());
	}

}
