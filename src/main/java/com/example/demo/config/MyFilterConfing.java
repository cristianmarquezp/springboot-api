package com.example.demo.config;

import com.example.demo.filter.myNewFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyFilterConfing {
    @Bean
    public FilterRegistrationBean<myNewFilter> registrationBean(){
        FilterRegistrationBean<myNewFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new myNewFilter());
        registrationBean.addUrlPatterns("/customers/*");
        return registrationBean;
    }
}
