package com.databeats.databeats.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.databeats.databeats.model.Song;


@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
   
   @Modifying
   @Query(value="DELETE FROM song WHERE song_id = : song_id", nativeQuery=true)
   public void findByDeletedSongId (@Param("song_id") Long song_id) ;
   
   @Modifying
   @Query(value="UPDATE song SET song_title = : song_Title WHERE song_id= : song_Id", nativeQuery= true)
   public List<Song> getUpdatedSongTitle(@Param("song_Title") String song_Title);
  
   @Query(value="SELECT * FROM song NATURAL JOIN album WHERE song_id= : song_Id", nativeQuery= true)
   public List<Song>  getSongInfo(@Param("song_Id") Long song_Id);
   
   @Query(value= "SELECT count(song_id) as 'Number of Songs' , SUM(duration) as 'Album duration', song_Id FROM song GROUP BY song_id"+
   " ORDER BY song_id ASC",nativeQuery = true )
   public List<Song> findBySongIndex(@Param("song_Id") Long song_Id);

   
}
