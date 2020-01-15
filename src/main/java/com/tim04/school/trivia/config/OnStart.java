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
        userService.save("admin", passwordEncoder.encode("admin"), 20, "ADMIN");
        subjectService.save("Cand s-a nascut Mihai Eminescu?", "15 ianuarie 1850");

    }
}
