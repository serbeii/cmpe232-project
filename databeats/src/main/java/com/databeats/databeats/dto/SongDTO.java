package com.databeats.databeats.dto;

import com.databeats.databeats.model.Song;

public class SongDTO {

    private String song_title;
    private int duration;

    public SongDTO() {
    }

    public SongDTO(String song_title, int duration) {
        this.song_title = song_title;
        this.duration = duration;
    }

    public SongDTO(String song_title, String duration) {
        this.song_title = song_title;
        this.duration = Integer.parseInt(duration);
    }

    public SongDTO(Song song) {
        this.song_title = song.getSongTitle();
        this.duration = song.getDuration();
    }

    public String getSong_title() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
