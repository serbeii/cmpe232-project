package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
}

