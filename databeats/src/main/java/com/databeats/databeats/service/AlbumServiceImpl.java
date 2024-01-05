package com.databeats.databeats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    
    @Override
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

}
