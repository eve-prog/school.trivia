package com.tim04.school.trivia.persistence.subject;

import javax.persistence.*;

@Entity
@Table(name = "subject")

public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name")
    private String name;

}
