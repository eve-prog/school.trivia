package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.subject.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

}
