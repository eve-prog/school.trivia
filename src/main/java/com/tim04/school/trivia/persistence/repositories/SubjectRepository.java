package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
