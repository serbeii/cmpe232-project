package com.databeats.databeats.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Album;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Modifying
    @Query(value = "UPDATE Album SET album_title = :newTitle WHERE album_title = :oldTitle", nativeQuery = true)
    void updateAlbumTitle(@Param("oldTitle") String oldTitle, @Param("newTitle") String newTitle);

    /*
     * @Modifying
     * 
     * @Query(value =
     * "ALTER TABLE Album ADD CONSTRAINT unique_album_title UNIQUE (album_title) WHERE album_title=: albumTitle"
     * , nativeQuery = true)
     * void addUniqueConstraint(@Param("albumTitle") String albumTitle);
     */
    @Query(value = "SELECT album.album_title,album.album_id,album.artist_id,album.genre,album.release_date " +
            "FROM album JOIN artist ON album.artist_id =artist.artist_id WHERE album.album_id= :albumID", nativeQuery = true)
    public List<Album> getAlbumInfo(@Param("albumID") Long albumID);

    @Query(value = "SELECT * FROM album WHERE album_id = :album_id", nativeQuery = true)
    public List<Album> getAlbum(@Param("album_id") long album_id);

    @Modifying
    // Cannot delete or update a parent row: a foreign key constraint fails
    // (`cmpe232`.`song`,
    // CONSTRAINT `FKrcjmk41yqj3pl3iyii40niab0` FOREIGN KEY (`album_id`) REFERENCES
    // `album` (`album_id`))
    @Query(value = "DELETE FROM album WHERE album_id = : album_id", nativeQuery = true)
    public void deleteAlbum(@Param("album_id") Long album_id);

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

    @Query(value = "SELECT * FROM album WHERE album_title LIKE %:substring% AND album_id > 1 ORDER BY album_title ASC", nativeQuery = true)
    List<Album> searchAlbum(@Param("substring") String substring);

    @Query(value = "SELECT COUNT(DISTINCT s.song_id) AS totalTracks, a.album_id, a.album_title, a.artist_id " +
            "FROM album a LEFT JOIN song s ON a.album_id = s.album_id WHERE a.artist_id = :artist_id " +
            "GROUP BY a.album_id, a.album_title, a.artist_id", nativeQuery = true)
    List<Object[]> findArtistDiscography(@Param("artist_id") long artist_id);

    @Query(value = "SELECT a.artist_id FROM album a WHERE a.album_title = :albumName", nativeQuery = true)
    Long findArtistIdByAlbumName(@Param("albumName") String albumName);
}
