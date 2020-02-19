package com.library.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.library.controller")
@ComponentScan("com.library.service")
@ComponentScan("com.library.mapper")
public class WebConfiguration implements WebMvcConfigurer {

}
