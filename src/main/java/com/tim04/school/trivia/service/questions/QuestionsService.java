package com.tim04.school.trivia.service.questions;

import com.tim04.school.trivia.controllers.QuestionsSaveBody;
import com.tim04.school.trivia.persistence.questions.QuestionsEntity;
import com.tim04.school.trivia.persistence.questions.QuestionsRepository;
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
        QuestionsEntity questionsEntity = new QuestionsEntity();
        questionsEntity.setDescription(questionsSaveBody.getDescription());
        questionsEntity.setLevel(questionsSaveBody.getLevel());
        questionsRepository.save(questionsEntity);
    }

    @Transactional(readOnly = true)
    public List<QuestionsEntity> findAll(){
        return questionsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<QuestionsEntity> searchByLevel(String level) {
        return questionsRepository.findByLevel(level);
    }
}
