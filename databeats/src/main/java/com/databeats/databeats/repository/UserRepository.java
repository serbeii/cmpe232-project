package com.databeats.databeats.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.databeats.databeats.model.User;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);
    
    @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<User> findByUsernameAndPassword(String username, String password);
}

