package com.databeats.databeats.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.databeats.databeats.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
