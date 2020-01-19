package com.tim04.school.trivia.persistence.questions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
}

