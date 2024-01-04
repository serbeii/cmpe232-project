package com.databeats.databeats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.model.Artist;
import com.databeats.databeats.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    @Transactional
    public boolean addArtist(Artist artist) {
        return (artistRepository.addArtist(artist.getArtistName()) > 0);
    }
    
    @Override
    @Transactional
    public boolean removeArtistByName(String artistName) {
        return (artistRepository.removeArtistByName(artistName) > 0);
    }
}
