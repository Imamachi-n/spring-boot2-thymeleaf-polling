package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondentRepository extends JpaRepository<Respondent, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
