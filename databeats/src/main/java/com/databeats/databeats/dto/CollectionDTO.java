package com.databeats.databeats.dto;

import java.time.LocalDate;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.model.Artist;

public class CollectionDTO {
    
    private String album_title;
    private LocalDate release_date;
    private String genre;
    private String artist_name;

    public CollectionDTO() {
    }

    public CollectionDTO(Album album, Artist artist) {
        this.album_title = album.getAlbumTitle();
        this.release_date = album.getReleaseDate();
        this.genre = album.getGenre();
        this.artist_name = artist.getArtistName();
    }

	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	public LocalDate getRelease_date() {
		return release_date;
	}
	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}


}
