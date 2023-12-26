package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Collection;
    
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long>{
}
