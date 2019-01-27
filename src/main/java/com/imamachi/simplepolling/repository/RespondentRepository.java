package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
