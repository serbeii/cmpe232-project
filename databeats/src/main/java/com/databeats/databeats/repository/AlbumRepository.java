package com.databeats.databeats.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Album;
import org.springframework.data.repository.query.Param;

import com.databeats.databeats.model.Artist;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Long>{
    @Query(value = "SELECT * FROM album WHERE artist_id = :artistId ", nativeQuery = true)
    Album findAlbumByArtistId(@Param("artistId") long artistId);

    @Query(value = "SELECT album_id FROM album WHERE album_title = :albumTitle ", nativeQuery = true)
    long findAlbumIdByAlbumName(@Param("albumTitle") String albumTitle);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO album (album_title, release_date, genre, artist_id) VALUES (:title, :releaseDate, :genre, :artist_id)", nativeQuery = true)
    void saveAlbum(@Param("title") String title,
                   @Param("releaseDate") LocalDate releaseDate,
                   @Param("genre") String genre,
                   @Param("artist_id") Long artist_id);
}
