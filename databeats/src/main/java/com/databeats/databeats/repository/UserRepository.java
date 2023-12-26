package com.databeats.databeats.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.databeats.databeats.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);
}

