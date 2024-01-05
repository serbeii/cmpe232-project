package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

   @Query(value = "SELECT artist_id FROM artist WHERE artist_name = :artistName", nativeQuery =true)
   Long findArtistIdByArtistName(@Param("artistName") String artistName);
}
