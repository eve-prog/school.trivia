package com.tim04.school.trivia.persistence.repositories;

import com.tim04.school.trivia.persistence.roles.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
