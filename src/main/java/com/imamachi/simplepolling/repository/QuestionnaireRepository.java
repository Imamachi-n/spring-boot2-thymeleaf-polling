package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
