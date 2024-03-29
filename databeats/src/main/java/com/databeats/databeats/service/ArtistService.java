package com.databeats.databeats.service;

import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Artist;

public interface ArtistService {
    @Transactional
    public boolean addArtist(Artist artist);

    @Transactional
    public boolean removeArtistByName(String name);

    public Artist getArtistByName(String name);
    public long getArtistIdByAlbum(String albumName);
    public String getArtistNameByAlbum(String albumName);
}
