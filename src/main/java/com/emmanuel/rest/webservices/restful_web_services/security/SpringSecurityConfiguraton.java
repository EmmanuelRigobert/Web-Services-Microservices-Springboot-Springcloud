package com.emmanuel.rest.webservices.restful_web_services.security;
/*
*
* */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//Spring security CSRF affects API calls. 403 for POST methods. To resolve this, we override Springboot security configuration filters
//Filters:
//1. UsernamePasswordAuthenticationFilter: This filter is used to authenticate the user based on the username and password provided in the login form
//2. In case of unsuccessful authentication, a web page is displayed for login
//3. CSRF (Cross-Site Request Forgery) is enabled by default in Spring Security. The main goal is to disable CSRF protection so we can send POST and PUT requests

@Configuration // A Configuration class indicates that the class can be used by the Spring IoC container as a source of bean definitions
public class SpringSecurityConfiguraton {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // SecurityFilterChain is used to build the security filter chain
        //1. UsernamePasswordAuthenticationFilter: This filter is used to authenticate the user based on the username and password provided in the login form
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated() // This method is used to authorize all HTTP requests
        );
        //2. In case of unsuccessful authentication, a web page is displayed for login
        http.httpBasic(withDefaults());//This displays a popup for login
        //3. CSRF (Cross-Site Request Forgery) is enabled by default in Spring Security. The main goal is to disable CSRF protection so we can send POST and PUT requests
        http.csrf().disable(); // This disables CSRF protection
        return http.build();
    }
}
