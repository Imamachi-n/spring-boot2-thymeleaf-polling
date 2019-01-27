package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.QuestionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDetailRepository extends JpaRepository<QuestionDetail, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
