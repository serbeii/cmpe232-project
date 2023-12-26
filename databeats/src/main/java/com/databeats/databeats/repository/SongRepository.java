package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
}
