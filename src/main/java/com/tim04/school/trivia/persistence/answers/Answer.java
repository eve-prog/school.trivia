package com.tim04.school.trivia.persistence.answers;

import com.tim04.school.trivia.persistence.questions.Question;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answer_id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "question_id")
//    @JoinColumn
    private Question question;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
