package com.tim04.school.trivia.persistence.questions;

import com.tim04.school.trivia.persistence.answers.Answer;
import com.tim04.school.trivia.persistence.subject.Subject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long question_id;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private String level;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
 //   @JoinColumn
    private Subject subject;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

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
