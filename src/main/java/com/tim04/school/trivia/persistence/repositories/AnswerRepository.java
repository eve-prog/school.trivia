package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.answers.AnswersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswersEntity, Long> {
}

