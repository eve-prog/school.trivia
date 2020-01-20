package com.tim04.school.trivia.persistence.questions;

import com.tim04.school.trivia.persistence.answers.AnswersEntity;
import com.tim04.school.trivia.persistence.subject.SubjectEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
public class QuestionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long question_id;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private String level;

    @ManyToOne
    private SubjectEntity subject;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="QUESTION_ID")
    private Set<AnswersEntity> answers;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
