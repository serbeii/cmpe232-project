package com.databeats.databeats.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.model.Album;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.repository.AlbumRepository;
import com.databeats.databeats.repository.ArtistRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;
   
    @Autowired 
    ArtistRepository artistRepository;

    @Autowired
    ArtistService artistService;

    @Override
    @Transactional
    public void saveAlbum(AlbumDTO albumDTO) {
        String artistName = albumDTO.getArtistName();
        String title = albumDTO.getTitle();
        LocalDate releaseDate = albumDTO.getReleaseDate();
        String genre = albumDTO.getGenre();
        Artist artist = artistService.getArtistByName(artistName);

        if (artist == null) {
            // If the artist is not found, create a new one and save it
            artistRepository.addArtist(artistName);
            artist = artistService.getArtistByName(artistName);
        }   

        System.out.println(title);
        // Now, you should have the artist available (either existing or newly created)
    
        //Album album = new Album();
        //album.setAlbumTitle(title);
        //album.setReleaseDate(java.sql.Date.valueOf(releaseDate));
        //album.setGenre(genre);
        //album.setArtist(artist);

        //albumRepository.save(album);
        albumRepository.saveAlbum(title, releaseDate, genre, artist.getArtistId());
    }
}
