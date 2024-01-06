package com.databeats.databeats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.repository.AlbumRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumServiceImp implements AlbumService {

    private  AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImp(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional
    public String updateAlbumTitle(String oldTitle, String newTitle) {
        albumRepository.updateAlbumTitle(oldTitle, newTitle);
        return newTitle;
    }

    @Override
    @Transactional
    public List<Album> getAlbumInfo(long albumID) {
        return albumRepository.getAlbumInfo(albumID);
    }

    @Override
    @Transactional
    public Long deleteAlbum(long album_id) {
        albumRepository.deleteById(album_id);
        return album_id;
    }
}
    

