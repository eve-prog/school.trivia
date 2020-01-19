package com.tim04.school.trivia.persistence.answers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswersEntity, Long> {
}
