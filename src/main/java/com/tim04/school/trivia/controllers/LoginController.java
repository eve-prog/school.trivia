package com.tim04.school.trivia.controllers;

import com.tim04.school.trivia.config.DatabaseUserDetailsService;
import com.tim04.school.trivia.config.JwtProvider;
import com.tim04.school.trivia.persistence.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtProvider jwtProvider;

    @Autowired
    private final DatabaseUserDetailsService userDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, DatabaseUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken( @RequestBody @Valid UserDto userAuthenticationRequest) throws Exception {
        authenticate(userAuthenticationRequest.getUsername(), userAuthenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userAuthenticationRequest.getUsername());
        final String token = jwtProvider.createToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            LOGGER.info("Login successfull");
        } catch (DisabledException e) {
            LOGGER.error("User disabled");
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            LOGGER.error("Invalid Credentials");
            throw new Exception("Invalid Credentials", e);
        }
    }
}
