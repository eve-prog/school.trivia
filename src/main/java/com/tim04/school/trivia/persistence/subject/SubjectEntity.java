package com.tim04.school.trivia.persistence.subject;

import com.tim04.school.trivia.persistence.questions.QuestionsEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subject_id;

    @Column(name = "name")
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="SUBJECT_ID")
    private Set<QuestionsEntity> questions;

}
