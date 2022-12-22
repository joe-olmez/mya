package com.olmez.mya.springsecurity.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.olmez.mya.springsecurity.securityutiliy.JwtUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthRequestFilter extends OncePerRequestFilter {

    private static final String HEADER_KEY = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_KEY);
        if (header == null || !header.startsWith(TOKEN_TYPE)) {
            // No JWT, so GET JWT
            filterChain.doFilter(request, response);
            return;
        }

        // Check JWT in request
        String jwt = getJWT(header);
        String username = JwtUtility.extractUsername(jwt);

        if (isRequiredAuth(username)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (JwtUtility.isTokenValid(userDetails, jwt)) {
                giveAuth(request, userDetails);
            }
            log.info("A request is made by {}", username);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * It gives JSON Web Token (JWT) by removing the first 6 characters in the
     * header.
     * 
     * @param header is like "Bearer eyJhbGciOiJIU..."
     * @return JWT as a string (like eyJhbGciOiJIU...)
     */
    private String getJWT(String header) {
        return header.substring(7);
    }

    private boolean isRequiredAuth(String username) {
        return (username != null) && (SecurityContextHolder.getContext().getAuthentication() == null);
    }

    private void giveAuth(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
