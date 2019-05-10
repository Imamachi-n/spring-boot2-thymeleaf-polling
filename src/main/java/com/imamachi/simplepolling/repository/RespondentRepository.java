package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.model.Respondent;
import com.imamachi.simplepolling.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
    List<Respondent> getByNameAndAndQuestionnaire(String name, Questionnaire questionnaire);
}
