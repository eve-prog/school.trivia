package com.tim04.school.trivia.persistence.roles;

import javax.persistence.*;

@Entity
@Table(name = "roles")

public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;

    @Column(name ="role_name")
    private String role_name;
}
