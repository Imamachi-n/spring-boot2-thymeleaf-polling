package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById

    User findByUsername(String username);

    @Modifying
    @Query("DELETE FROM User WHERE userId IN (:userIds)")
    int deleteUserByUserId(@Param("userIds") List<Integer> userIds);
}
