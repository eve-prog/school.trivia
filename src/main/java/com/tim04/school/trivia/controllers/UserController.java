package com.tim04.school.trivia.controllers;

import com.tim04.school.trivia.persistence.user.UserEntity;
import com.tim04.school.trivia.persistence.user.UserRepository;
import com.tim04.school.trivia.service.user.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    //private CartRepository cartRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> findById( @PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> findByUserName(@PathVariable String username) {
        UserEntity user = userRepository.findByUsername(username);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(createUserRequest.getUsername());
        if(!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            LOGGER.error("The password for user " + createUserRequest.getUsername() + " does not match!");
            return ResponseEntity.badRequest().build();
        }

        if(createUserRequest.getPassword().length() < 8 || createUserRequest.getPassword().length() > 10) {
            LOGGER.error("The password for user " + createUserRequest.getUsername() + " does not match the password requirements!");
            return ResponseEntity.badRequest().build();
        }

        LOGGER.info("[User success] The user " + createUserRequest.getUsername() + " was successfully created");
        user.setPassword(encoder().encode(createUserRequest.getPassword()));

        /*Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);*/
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}

