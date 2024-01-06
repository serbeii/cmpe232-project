package com.databeats.databeats.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.databeats.databeats.model.UserAlbumCollectionView;

import java.util.List;

public interface UserAlbumCollectionRepository extends CrudRepository<UserAlbumCollectionView, Long> {

    @Query(value = "SELECT u.username AS username, u.user_id as user_id, a.album_title AS album_title, c.id AS collection_id " +
                   "FROM users u " +
                   "LEFT JOIN collection c ON u.user_id = c.user_id " +
                   "LEFT JOIN album a ON c.album_id = a.album_id " +
                   "GROUP BY u.user_id, a.album_id, c.id", nativeQuery = true)
    List<UserAlbumCollectionView> getUserAlbumCollection();

}
