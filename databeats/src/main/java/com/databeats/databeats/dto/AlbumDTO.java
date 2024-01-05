package com.databeats.databeats.dto;

import java.time.LocalDate;

public class AlbumDTO {

    private String title;
    private LocalDate releaseDate;
    private String genre;
    private String artistName;
    
    public AlbumDTO() {
    }

    public AlbumDTO(String title, String releaseDate, String genre, String artistName) {
        this.title = title;
        this.genre = genre;
        this.artistName = artistName;
        this.releaseDate = LocalDate.parse(releaseDate);
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
