package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
