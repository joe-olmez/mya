package com.olmez.mya.security.springsecurity.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.olmez.mya.model.User;
import com.olmez.mya.security.authentication.AuthenticationContextContainer;
import com.olmez.mya.security.authentication.AuthenticationContextImpl;
import com.olmez.mya.services.impl.ApiTokenService;
import com.olmez.mya.utility.StringUtility;

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
public class SecurityInterceptor implements HandlerInterceptor {

    public static final String API_TOKEN = "api-token";

    private final ApiTokenService apiTokenService;
    private final AuthenticationContextContainer contextContainer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        User curUser = null;
        String token = request.getHeader(API_TOKEN);
        if (!StringUtility.isEmpty(token)) {
            curUser = authenticateApiToken(token, request, handler);
        } else {
            // curUser = vaadinSessionService.getCurrentUser();
        }

        if (curUser == null) {
            response.setStatus(401);
            response.getOutputStream().print("Invalid user!");
            log.info("Validation not OK! Remote address:{} Url:{}",
                    request.getRemoteAddr(), request.getRequestURL());
            return false;
        }

        // create a new auth context for every single request
        var authContext = new AuthenticationContextImpl(curUser);
        contextContainer.setContext(authContext);

        log.info("Validated user:{} Remote address:{} Url:{}", curUser,
                request.getRemoteAddr(),
                request.getRequestURL());
        return true;

    }

    public User authenticateApiToken(String token, HttpServletRequest request, Object handler) {
        // if (!isAllowApiToken(handler)) {
        // log.info("Validation not OK! Remote address:{} Url:{}",
        // request.getRemoteAddr(), request.getRequestURL());
        // return null;
        // }
        User user = apiTokenService.authenticateApiToken(token);
        if (user == null) {
            return null;
        }

        log.info("A non-login user, {}, made a request on {}", user.getUsername(), LocalDateTime.now());
        return user;
    }

    /**
     * Checks whether the method has AllowApiToken annotation.
     * 
     * @param handler chosen handler to execute, for the instance evaluation
     * @return If the method has AllowApiToken annotation, it returns true,
     *         otherwise, it returns false.
     */
    private boolean isAllowApiToken(Object handler) {
        var allowApiToken = false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            allowApiToken = handlerMethod.getMethod().getAnnotation(AllowApiToken.class) != null;
        }
        return allowApiToken;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        // unlockVaadinSession();
    }

    // private void unlockVaadinSession() {
    // var oSession = vaadinSessionService.getCurrentSession();
    // if (oSession.isPresent()) {
    // VaadinSession session = oSession.get();
    // if (session != null && session.hasLock()) {
    // session.unlock();
    // }
    // }
    // }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AllowApiToken {
    }

}
