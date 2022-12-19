package com.olmez.mya.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.olmez.mya.model.securitydata.UserRoles;

public enum UserType {

	/**
	 * Given all permissions and application not person
	 */
	APPLICATION("Application"),

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
	TEAM("Team");

	private final String label;
	private final @Nullable String role;

	private UserType(String label) {
		this(label, null);
	}

	private UserType(String label, @Nullable String role) {
		this.label = label;
		this.role = role;
	}

	@Override
	public String toString() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public static List<UserType> getValuesWithoutTeam() {
		List<UserType> newList = new ArrayList<>();
		for (UserType type : Arrays.asList(UserType.values())) {
			if (!type.equals(UserType.TEAM)) {
				newList.add(type);
			}
		}
		return newList;
	}

	/**
	 * Gives the user's role. A role named {@link UserRoles#ROLE_ADMIN} is currently
	 * provided for admin users only. Non-admin users do not have a role.
	 * 
	 * @return If the user does not have a role, it is empty, if it does, the role
	 *         returns.
	 */
	public Optional<String> getRole() {
		return Optional.ofNullable(role);
	}

}
