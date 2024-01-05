package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Collection;
import java.util.*;


import jakarta.transaction.Transactional;
    
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO collection (user_id, album_id) VALUES (:userId, :albumId)", nativeQuery = true)
    void addAlbumtoCollection(@Param("userId") long userId, @Param("albumId") long albumId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collection WHERE user_id = :userId AND album_id = :albumId", nativeQuery = true)
    void deleteAlbumFromCollection(@Param("userId") long userId, @Param("albumId") long albumId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collection WHERE user_id = :userId OR  :userId IS NULL", nativeQuery = true)
    void deleteEntireCollection(@Param("userId") Optional<Long> userId);

    /*@Query(value = "SELECT * FROM collection WHERE album_id = :albumId", nativeQuery = true)
    List<Collection> viewArtistDiscographyInCollection(@Param("albumId") long userId);*/

}
