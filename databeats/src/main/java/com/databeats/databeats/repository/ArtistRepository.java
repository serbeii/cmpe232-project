package com.databeats.databeats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
@EnableJpaRepositories
public interface ArtistRepository extends JpaRepository<Artist, Long>{

    @Query(value = "SELECT artist_id FROM artist WHERE artist_name = :artistName", nativeQuery =true)
    Long findArtistIdByArtistName(@Param("artistName") String artistName);
  
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO artist (artist_name) VALUES (:artistName)", nativeQuery = true) 
    public int addArtist(@Param("artistName") String artistName); 

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM artist WHERE artist_name = (:artistName)", nativeQuery = true)
    public int removeArtistByName(@Param("artistName") String artistName);

    @Query(value = "SELECT * FROM artist WHERE artist_name = :artistName", nativeQuery = true)
    public List<Artist> getArtistByName(@Param("artistName") String artistName);


}
