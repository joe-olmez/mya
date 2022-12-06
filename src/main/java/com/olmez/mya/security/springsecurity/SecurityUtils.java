package com.olmez.mya.security.springsecurity;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

/**
 * This makes the access control checking itself. (See
 * {@link MainLayout#userAccessControl}
 */
@UtilityClass
public class SecurityUtils {
    /**
     * It checks the current user's access to the class that comes as a parameter.
     * 
     * @param securedClass class to be accessed
     * @return If there is no access permission, it returns false.
     */
    public static boolean isAccessGranted(Class<?> securedClass) {
        // non-admin users cannot access classes or methods with
        // @Secured(User.ROLE_ADMIN)
        Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
        // Allow access if the role is not required or the view is not protected (it
        // means no @Secured(User.ROLE_ADMIN)).
        if (secured == null) {
            return true;
        }

        // When an admin user emulates another user, the SecurityContextHolder still
        // keeps {@link UserRoles.ROLE_ADMIN} (No other role is currently available). So
        // if the emulated user is not admin then limit access
        // privileges.
        // var curUserService = BaseObject.getCurrentUserService();
        // if (!curUserService.getUser().isAdmin()) {
        // return false;
        // }

        // lookup needed role in user roles (The role means userType)
        List<String> allowedRoles = Arrays.asList(secured.value());
        Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();

        // Iterate the all authorities the user has and check if access can be granted.
        return userAuth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(allowedRoles::contains);
    }

    /**
     * Gives whether the current user is logged in.
     * 
     * @return Returns true if logged in, false otherwise
     */
    public static boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Check for AnonymousAuthenticationToken Spring Security assigns by default to
        // non-logged in users.
        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }

}
