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

       // userService.save("admin", "admin", "aa@aa.com", "123", "", passwordEncoder.encode("admin") , "ADMIN");

        subjectService.save("Literatura");
        subjectService.save("Music");
        subjectService.save("Math");
        //userService.save("admin", "admin", "trivia", "0766757888", "active", passwordEncoder.encode("admin"), "user");
        userService.save("admin", "admin", "trivia", "blablabla@gmail.com)", "0766767888", "active", passwordEncoder.encode("adminadmin"), "user");
    }
}
