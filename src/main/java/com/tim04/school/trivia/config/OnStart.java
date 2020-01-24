package com.tim04.school.trivia.config;

import com.tim04.school.trivia.service.subject.SubjectService;
import com.tim04.school.trivia.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OnStart implements ApplicationRunner {
    private final UserService userService;
    private final SubjectService subjectService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OnStart(UserService userService, SubjectService subjectService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
<<<<<<< HEAD
=======
       // userService.save("admin", "admin", "aa@aa.com", "123", "", passwordEncoder.encode("admin") , "ADMIN");
>>>>>>> 536291328f76b9eb5aa9b69a6dd14f3f1a8be8e3
        subjectService.save("Literatura");
        subjectService.save("Music");
        subjectService.save("Math");
        userService.save("admin", "admin", "blabla@gmil.com", "0766757888", "active", passwordEncoder.encode("admin"), "user");

    }
}
