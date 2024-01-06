package com.databeats.databeats.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.model.Album;

@Service
public interface AlbumService {
    String updateAlbumTitle(String oldTitle, String newTitle);
    List<Album> getAlbumInfo(long albumID);
    Long deleteAlbum(long album_id);
    public void saveAlbum(AlbumDTO albumDTO);
}
