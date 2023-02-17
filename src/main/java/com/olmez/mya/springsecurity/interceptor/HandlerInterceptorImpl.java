package com.olmez.mya.springsecurity.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This service performs security control of incoming requests by Spring. It
 * performs a security check for every received request. If the request is
 * secure, a specific context to this request is created and information about
 * the request (such as the user) is kept in this context throughout the
 * request.
 */
@Component
@AllArgsConstructor
@Slf4j
public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle...");
        // This runs before user authentication via AuthRestController. If we want to
        // put a specific security point, we should put the logic here.
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception e) {
        if (e != null) {
            log.info("afterCompletion...{}", e.getMessage());
        }
    }

}
