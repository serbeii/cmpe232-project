package com.databeats.databeats.dto;

import java.time.LocalDate;

import com.databeats.databeats.model.Album;

public class AlbumDTO {

    private String title;
    private LocalDate releaseDate;
    private String genre;
    private String artistName;
    private int duration;
    private int totalTracks;
    
    public AlbumDTO() {
    }

    public AlbumDTO(String title, String releaseDate, String genre, String artistName) {
        this.title = title;
        this.genre = genre;
        this.artistName = artistName;
        this.releaseDate = LocalDate.parse(releaseDate);
    }

    public AlbumDTO(String title, LocalDate releaseDate, String genre, String artistName) {
        this.title = title;
        this.genre = genre;
        this.artistName = artistName;
        this.releaseDate = releaseDate;
    }

    public AlbumDTO(Album album) {
        this.title = album.getAlbumTitle();
        this.genre = album.getGenre();
        this.artistName = album.getArtist().getArtistName();
        this.releaseDate = album.getReleaseDate();
    }

    public AlbumDTO(Album album, int duration) {
        this.title = album.getAlbumTitle();
        this.genre = album.getGenre();
        this.artistName = album.getArtist().getArtistName();
        this.releaseDate = album.getReleaseDate();
        this.duration = duration;
    }

    public AlbumDTO(int totalTracks, Album album) {
        this.title = album.getAlbumTitle();
        this.genre = album.getGenre();
        this.artistName = album.getArtist().getArtistName();
        this.releaseDate = album.getReleaseDate();
        this.totalTracks = totalTracks;
    }

	public int getTotalTracks() {
		return totalTracks;
	}

	public void setTotalTracks(int totalTracks) {
		this.totalTracks = totalTracks;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

}
