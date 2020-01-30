package com.library.configuration;

import com.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth, UsersService userDetailsService) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/books/", "/login").permitAll()
                .antMatchers("/books/**").access("hasRole('USER') and hasRole('ADMIN')")
                .antMatchers("/genres/**", "/authors/**", "/publishing/**").access("hasRole('ADMIN')")
                .and().formLogin();
    }
}
