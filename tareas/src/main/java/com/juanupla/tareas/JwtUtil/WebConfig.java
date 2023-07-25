package com.juanupla.tareas.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;



@Configuration
public class WebConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterRegistration() {
        FilterRegistrationBean<JwtTokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtTokenFilter);
        registration.addUrlPatterns("/*"); // Aplica el filtro a todas las URL
        return registration;
    }
}
