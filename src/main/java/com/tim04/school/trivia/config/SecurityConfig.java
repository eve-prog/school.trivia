package com.tim04.school.trivia.config;

import com.tim04.school.trivia.service.user.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DatabaseUserDetailsService userService;

    @Autowired
    public SecurityConfig(DatabaseUserDetailsService userService) {
        this.userService = userService;
    }

    // apoi se apeleaza config.configure(AuthenticationManagerBuilder);
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // config.configure(HttpSecurity);
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.cors().disable()
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
        //@formatter:on
    }
}