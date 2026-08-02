package com.theatre_directory.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theatre_directory.security.AuthFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(
            AuthFilter authFilter) {

        FilterRegistrationBean<AuthFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(authFilter);

        registration.addUrlPatterns("/theatres");
        registration.addUrlPatterns("/theatres/*");

        registration.addUrlPatterns("/reviews");
        registration.addUrlPatterns("/reviews/*");
        
        registration.addUrlPatterns("/movies");
        registration.addUrlPatterns("/movies/*");

        registration.addUrlPatterns("/theatre-movies");
        registration.addUrlPatterns("/theatre-movies/*");

        return registration;
    }
}