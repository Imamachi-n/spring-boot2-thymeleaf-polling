package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.ResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultDetailRepository extends JpaRepository<ResultDetail, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
}
