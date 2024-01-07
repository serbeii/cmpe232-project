package com.databeats.databeats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.dto.SongDTO;
import com.databeats.databeats.repository.AlbumRepository;
import com.databeats.databeats.repository.ArtistRepository;
import com.databeats.databeats.repository.SongRepository;

@Service
public class SongServiceImp implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    @Transactional
    public void addSongs(List<SongDTO> songDTO, AlbumDTO albumDTO) {
        long artist_id = artistRepository.findArtistIdByArtistName(albumDTO.getArtistName());
        long album_id = albumRepository.findAlbumIdByAlbumName(albumDTO.getTitle()).get(0);
        for (int i = 1; i <= songDTO.size(); i++) {
            SongDTO songDTO2 = songDTO.get(i - 1);
            songRepository.addSong(i, songDTO2.getSong_title(), songDTO2.getDuration(),
                    album_id, artist_id);
        }
    }
}
