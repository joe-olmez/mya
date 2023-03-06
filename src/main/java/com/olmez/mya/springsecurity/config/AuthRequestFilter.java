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
    private static final String TOKEN_TYPE = "Bearer ";

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader(HEADER_KEY);
        if (!hasToken(header)) {
            filterChain.doFilter(request, response); // No jwt then generate one
            return;
        }

        // Look at jwt in request. Get jwt from header by removing the first 6
        // characters. ("Bearer eyJhbGciOiJIU...")
        final String jwt = header.substring(7);
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

    private boolean hasToken(String header) {
        return (header != null) && (header.startsWith(TOKEN_TYPE)) && header.length() > 20;
    }

    private boolean isRequiredAuth(String username) {
        return (username != null) && (SecurityContextHolder.getContext().getAuthentication() == null);
    }

    private void giveAuth(HttpServletRequest request, UserDetails userDetails) {
        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
