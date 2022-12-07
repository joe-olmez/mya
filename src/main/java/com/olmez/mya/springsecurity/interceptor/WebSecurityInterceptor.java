// package com.olmez.mya.springsecurity.interceptor;

// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.stereotype.Component;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;

// import com.olmez.mya.model.User;
// import com.olmez.mya.utility.StringUtility;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// /**
// * This service performs security control of incoming requests by Spring. It
// * performs a security check for every received request. If the request is
// * secure, a specific context to this request is created and information about
// * the request (such as the user) is kept in this context throughout the
// * request.
// */
// @Component
// @AllArgsConstructor
// @Slf4j
// public class WebSecurityInterceptor implements HandlerInterceptor {

// public static final String API_TOKEN = "api-token";

// @Override
// public boolean preHandle(HttpServletRequest request, HttpServletResponse
// response, Object handler)
// throws Exception {

// return true;

// }

// @Override
// public void afterCompletion(HttpServletRequest request, HttpServletResponse
// response, Object handler,
// Exception ex) {
// }

// @Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.METHOD)
// public @interface AllowApiToken {
// }

// }
