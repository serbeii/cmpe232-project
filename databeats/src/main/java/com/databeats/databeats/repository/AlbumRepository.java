package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.databeats.databeats.model.Album;
import org.springframework.data.repository.query.Param;



@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
    @Query(value = "SELECT * FROM album WHERE artist_id = :artistId ", nativeQuery = true)
    Album findAlbumByArtistId(@Param("artistId") long artistId);

    @Query(value = "SELECT album_id FROM album WHERE album_title = :albumTitle ", nativeQuery = true)
    long findAlbumIdByAlbumName(@Param("albumTitle") String albumTitle);

}
