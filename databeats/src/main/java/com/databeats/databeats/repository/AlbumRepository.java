package com.databeats.databeats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.model.Song;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Long>{
    @Modifying
    @Query(value ="UPDATE Album SET album_title = :newTitle WHERE album_title = :oldTitle", nativeQuery=true)
    void updateAlbumTitle(@Param("oldTitle") String oldTitle, @Param("newTitle") String newTitle);
    
    /*  @Modifying
    @Query(value = "ALTER TABLE Album ADD CONSTRAINT unique_album_title UNIQUE (album_title) WHERE album_title=: albumTitle", nativeQuery = true)
    void addUniqueConstraint(@Param("albumTitle") String albumTitle);
    */
    @Query(value="SELECT album.album_title,album.album_id,album.artist_id,album.genre,album.release_date "+
    "FROM album JOIN artist ON album.artist_id =artist.artist_id WHERE album.album_id= :albumID", nativeQuery=true )
    public List<Album>  getAlbumInfo(@Param("albumID") Long albumID);
    
    @Modifying
    //Cannot delete or update a parent row: a foreign key constraint fails (`cmpe232`.`song`, 
    //CONSTRAINT `FKrcjmk41yqj3pl3iyii40niab0` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`))
    @Query(value="DELETE FROM album WHERE album_id = : album_id", nativeQuery=true)
    public void deleteAlbum (@Param("album_id") Long album_id) ;

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
