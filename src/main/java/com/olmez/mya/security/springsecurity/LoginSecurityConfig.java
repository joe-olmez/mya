package com.olmez.mya.security.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class LoginSecurityConfig {

    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_URL = "/login";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        return http.build();

        // http.authorizeRequests().antMatcher(HttpMethod.GET, "/**").permitAll().
        //         // .antMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN", "USER")
        //         .antMatchers(HttpMethod.POST, "/**").permitAll()
        //         .antMatchers(HttpMethod.POST, "/**").permitAll()
        //         .antMatchers(HttpMethod.DELETE, "/**").permitAll().and().requestCache()
        //         .requestCache(new NullRequestCache()).and().httpBasic().and().csrf().disable();

        // http.formLogin(form -> {
        // form.loginPage(LOGIN_URL)
        // .permitAll();
        // }).authorizeRequests();
        //return http.build();
    }

    @Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.csrf().disable().authorizeRequests()
    // // require authentication for all Spring Actuator endpoints
    // .antMatchers("/bbdt/**")
    // // require Admin ("System Administrator") as authority
    // .hasAuthority(UserRoles.ROLE_ADMIN)
    // // redirect to webapp login page when user logouts via /logout
    // .and().logout().logoutUrl(LOGOUT_URL).logoutSuccessUrl(LOGIN_URL)
    // // use same login form (webapp login) for accessing the urls specified above
    // .and().formLogin().loginPage(LOGIN_URL);
    // return http.build();
    // }
}
