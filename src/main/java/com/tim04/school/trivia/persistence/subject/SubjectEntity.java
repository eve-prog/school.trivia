package com.tim04.school.trivia.persistence.subject;

import javax.persistence.*;

@Entity
@Table(name="subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subject_id;

    @Column(name = "name")
    private String name;

    
    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
