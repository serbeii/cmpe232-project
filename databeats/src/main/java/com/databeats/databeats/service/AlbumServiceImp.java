package com.databeats.databeats.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.repository.AlbumRepository;
import com.databeats.databeats.dto.AlbumBody;
import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.dto.SongDTO;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.model.Song;
import com.databeats.databeats.repository.ArtistRepository;
import com.databeats.databeats.repository.SongRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumServiceImp implements AlbumService {
    
    @Autowired
    AlbumRepository albumRepository;
   
    @Autowired 
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

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

    @Override
    public List<AlbumDTO> searchAlbum(String substring) {
        List<Album> albums = albumRepository.searchAlbum(substring);
        List<AlbumDTO> albumDTOs = new ArrayList<>();
        for (Album album : albums) {
             AlbumDTO e = new AlbumDTO(album.getAlbumTitle(), album.getReleaseDate(),
                album.getGenre(), album.getArtist().getArtistName());
             albumDTOs.add(e);
        } 
        return albumDTOs; 
    }

    @Override
    public AlbumBody viewAlbum(String album_name){
        long album_id = albumRepository.findAlbumIdByAlbumName(album_name);
        Album album = albumRepository.getAlbum(album_id).get(0);
        AlbumDTO albumDTO = new AlbumDTO(album);
        List<Song> song = songRepository.getSongsFromAlbum(album_id);
        List<SongDTO> songDTO = new ArrayList<>();
        for (Song song2 : song) {
            SongDTO e = new SongDTO(song2);
            songDTO.add(e);
        }
        AlbumBody body = new AlbumBody();
        body.setSongDTO(songDTO);
        body.setAlbumDTO(albumDTO);
        return body;
    }
}
