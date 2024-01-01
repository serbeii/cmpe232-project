package com.databeats.databeats.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.databeats.databeats.model.User;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);
    
    @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "INSERT INTO users (username, password, role) VALUES (:username, :password, :role)", nativeQuery = true)
    void addUser(@Param("username") String username, @Param("password") String password, @Param("role") String role);
}

