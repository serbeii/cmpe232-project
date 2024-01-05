package com.databeats.databeats.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Table(name = "UserAlbumCollectionView")
@Entity
@Immutable
public class UserAlbumCollectionView {
    @Id
    private Long collectionId;
    private Long user_id;
    private String username;
    private String albumTitle;

	public Long getCollectionId() {
		return collectionId;
	}
	public String getUsername() {
		return username;
	}
	public Long getUser_id() {
		return user_id;
	}
	public String getAlbumTitle() {
		return albumTitle;
	}

}
