package com.tim04.school.trivia.controllers;


import com.tim04.school.trivia.persistence.subject.SubjectEntity;
import com.tim04.school.trivia.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("subject")
@CrossOrigin(origins = "http://localhost:3306")
public class ServiceController {
    private final SubjectService subjectService;

    @Autowired
    public ServiceController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody SubjectSaveBody body){
        subjectService.save(body.getQuestions(),body.getAnswers());
    }

    @GetMapping("all")
    public List<SubjectEntity> all(){
        logUser();
        return subjectService.findAll();
    }

    private void logUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User authenticatedUser = (User) principal;
            System.out.println("User: " + authenticatedUser.getUsername());
        }else{
            System.out.println("Could not obtain username");
        }
    }
}
