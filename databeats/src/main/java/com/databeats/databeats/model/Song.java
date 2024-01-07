package com.databeats.databeats.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songId;

    @Column(unique = false)
    private int songIndex;

    @Column(unique = false)
    private int duration;
    private String songTitle;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "album_id")
    private Album album;

	public long getSongId() {
		return songId;
	}

	public void setSongId(long songId) {
		this.songId = songId;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public int getSongIndex() {
		return songIndex;
	}

	public void setSongIndex(int songIndex) {
		this.songIndex = songIndex;
	}
}
