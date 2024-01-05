package com.databeats.databeats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.databeats.databeats.model.UserAlbumCollectionView;

public interface UserAlbumCollectionRepository extends JpaRepository<UserAlbumCollectionView, Long> {

    @Query(value = "SELECT * FROM UserAlbumCollectionView", nativeQuery = true)
    List<UserAlbumCollectionView> getUserAlbumCollectionView();
}
