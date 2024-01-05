package com.databeats.databeats.model;

import org.hibernate.annotations.Subselect;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Subselect("SELECT c.id AS collection_id, u.userId AS user_id, u.username AS username, " +
           "a.albumTitle AS album_title " +
           "FROM Collection c " +
           "LEFT JOIN User u ON c.user_id = u.userId " +
           "LEFT JOIN Album a ON c.album_id = a.albumId " +
           "GROUP BY u.userId, c.id, a.albumTitle")
public class UserAlbumCollectionView {
    @Id
    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "album_title")
    private String albumTitle;

	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}   
}
