package com.tim04.school.trivia.controllers;

import com.tim04.school.trivia.persistence.questions.QuestionsEntity;
import com.tim04.school.trivia.service.questions.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
@CrossOrigin(origins = "http://localhost:3306")

public class QuestionsController {
    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody QuestionsSaveBody questionsSaveBody){
        questionsService.save(questionsSaveBody);
    }

    @GetMapping(value = "all")
    public List<QuestionsEntity> all(){
        return questionsService.findAll();
    }

    @GetMapping(value = "search/{level}")
    public List<QuestionsEntity> search(@PathVariable("level") String l){
        return questionsService.searchByLevel(l);
    }

}
