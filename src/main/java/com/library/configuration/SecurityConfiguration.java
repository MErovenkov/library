package com.library.configuration;

import com.library.service.UserService;
import com.library.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //System.out.println("tut");
        auth
                .inMemoryAuthentication()
                .withUser("123").password("123").roles("USER");
        //auth.userDetailsService(userDetailsService)/*.passwordEncoder(passwordEncoder);
    }


//todo: hasAnyRole
    @Override
    protected void configure(HttpSecurity http) throws Exception {
http.
        authorizeRequests()
        .antMatchers("/", "/books").permitAll()
        .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
        .and()
        .logout().permitAll();

    }
}*/