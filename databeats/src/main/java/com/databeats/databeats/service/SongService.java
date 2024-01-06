package com.databeats.databeats.service;

import java.util.List;

import com.databeats.databeats.dto.SongDTO;
import com.databeats.databeats.dto.AlbumDTO;

public interface SongService {
   public void addSongs(List<SongDTO> songDTO, AlbumDTO albumDTO); 
}
