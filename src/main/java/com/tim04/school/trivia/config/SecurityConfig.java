package com.tim04.school.trivia.config;

import com.tim04.school.trivia.service.user.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


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
//        http.cors().disable()
//                .formLogin()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();
        //links which do not require login
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout")
                .permitAll()
                .anyRequest().authenticated();

// handle form submission
        http.authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")// submit reuqest
                .loginPage("/login")
                .defaultSuccessUrl("/userinfo")
                .usernameParameter("username")
                .passwordParameter("password")
                //configure logout
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutDone")
                .permitAll();

        // No session will be created or used by spring security
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //@formatter:on
    }
}