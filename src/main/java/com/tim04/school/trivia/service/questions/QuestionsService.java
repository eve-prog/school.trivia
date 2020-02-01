package com.tim04.school.trivia.service.questions;

import com.tim04.school.trivia.controllers.QuestionsSaveBody;
import com.tim04.school.trivia.persistence.questions.Question;
import com.tim04.school.trivia.persistence.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Transactional
    public void save(QuestionsSaveBody questionsSaveBody){
        Question question = new Question();
        question.setDescription(questionsSaveBody.getDescription());
        question.setLevel(questionsSaveBody.getLevel());
        questionsRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<Question> findAll(){
        return questionsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Question> searchByLevel(String level) {
        return questionsRepository.findByLevel(level);
    }
}
