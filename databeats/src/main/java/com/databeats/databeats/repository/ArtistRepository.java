package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
}
