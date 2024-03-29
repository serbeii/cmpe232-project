package com.databeats.databeats.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.databeats.databeats.model.User;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (username, password, role) VALUES (:username, :password, :role)", nativeQuery = true)
    void addUser(@Param("username") String username, @Param("password") String password, @Param("role") String role);

    @Query(value = "SELECT * FROM users WHERE user_id = :userId", nativeQuery = true)
    User findById(long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE username = (:username)", nativeQuery = true)
    int removeUser(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = (:newUsername) WHERE username = (:oldUsername)", nativeQuery = true)
    int updateUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);
}
