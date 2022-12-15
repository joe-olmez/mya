package com.olmez.mya.springsecurity.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

/**
 * In order for Spring Boot to perform a security check of incoming requests,
 * this class allows adding our own securityInterceptor to SpringBoot's
 * interceptor.
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    private final HandlerInterceptorImpl handlerInterceptorImpl;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorImpl)
                .addPathPatterns("/**");
    }
}
