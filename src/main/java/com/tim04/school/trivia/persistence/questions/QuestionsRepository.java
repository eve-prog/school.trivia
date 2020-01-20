package com.tim04.school.trivia.persistence.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
    List<QuestionsEntity> findByLevel(String level);
}

