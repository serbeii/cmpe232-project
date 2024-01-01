package com.databeats.databeats.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long albumId;
    private String albumTitle;
    private Date releaseDate;
    private String genre;
    
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
