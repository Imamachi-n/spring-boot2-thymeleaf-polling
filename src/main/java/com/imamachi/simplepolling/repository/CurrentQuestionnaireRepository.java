package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentQuestionnaireRepository extends JpaRepository<CurrentQuestionnaire, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
