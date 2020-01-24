package com.tim04.school.trivia.persistence.answers;

import com.tim04.school.trivia.persistence.questions.QuestionsEntity;
import com.tim04.school.trivia.persistence.subject.SubjectEntity;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class AnswersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answer_id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private QuestionsEntity questions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
