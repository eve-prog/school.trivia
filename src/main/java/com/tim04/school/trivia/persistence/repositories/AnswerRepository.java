package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.answers.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

