package com.databeats.databeats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.repository.AlbumRepository;
import com.databeats.databeats.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @Override
    @Transactional
    public boolean addArtist(Artist artist) {
        return (artistRepository.addArtist(artist.getArtistName()) > 0);
    }

    @Override
    @Transactional
    public boolean removeArtistByName(String artistName) {
        Long artistId = artistRepository.findArtistIdByArtistName(artistName);
        List<Album> albums = albumRepository.findAlbumByArtistId(artistId);
        for (Album album : albums) {
            albumService.deleteAlbum(album.getAlbumTitle());
        }
        return (artistRepository.removeArtistByName(artistName) > 0);
    }

    @Override
    public Artist getArtistByName(String artistName) {
        List<Artist> artists = artistRepository.getArtistByName(artistName);

        if (!artists.isEmpty()) {
            return artists.get(0);
        } else {
            System.out.println("artist not found " + artistName);
            return null;
        }
    }

    @Override
    public long getArtistIdByAlbum(String albumName) {
        return albumRepository.findArtistIdByAlbumName(albumName);
    }

    @Override
    public String getArtistNameByAlbum(String albumName) {
        long artistId = albumRepository.findArtistIdByAlbumName(albumName);
        Optional<Artist> optionalArtist = artistRepository.findById(artistId);

        return optionalArtist.map(Artist::getArtistName).orElse("Unknown Artist");

    }
}
