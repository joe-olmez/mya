package com.olmez.mya.model.enums;

import com.olmez.mya.utility.UserRoles;

public enum UserType {

	/**
	 * Given all permissions and application not person
	 */
	APPLICATION("Application", UserRoles.ROLE_APP),

	/**
	 * Given all permissions
	 */
	ADMIN("System Admin", UserRoles.ROLE_ADMIN),
	/**
	 * Standard user
	 */
	REGULAR("Regular User", UserRoles.ROLE_USER),
	/**
	 * Team
	 */
	TEAM("Team", UserRoles.ROLE_USER);

	private final String name;
	private final String role;

	private UserType(String label, String role) {
		this.name = label;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

}
