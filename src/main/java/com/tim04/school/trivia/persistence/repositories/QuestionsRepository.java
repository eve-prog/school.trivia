package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findByLevel(String level);
}

