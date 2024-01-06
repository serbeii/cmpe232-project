package com.databeats.databeats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.repository.AlbumRepository;
import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.repository.ArtistRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumServiceImp implements AlbumService {
    
    @Autowired
    AlbumRepository albumRepository;
   
    @Autowired 
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

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

    @Override
    @Transactional
    public void saveAlbum(AlbumDTO albumDTO) {
        String artistName = albumDTO.getArtistName();
        String title = albumDTO.getTitle();
        LocalDate releaseDate = albumDTO.getReleaseDate();
        String genre = albumDTO.getGenre();
        Artist artist = artistService.getArtistByName(artistName);

        if (artist == null) {
            artistRepository.addArtist(artistName);
            artist = artistService.getArtistByName(artistName);
        }   
        albumRepository.saveAlbum(title, releaseDate, genre, artist.getArtistId());
    }
}
    

