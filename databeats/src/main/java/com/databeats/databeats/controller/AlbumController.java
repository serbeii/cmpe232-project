package com.databeats.databeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.service.AlbumService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/album")

public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/add")
    public String add(@RequestBody Album album) {
        albumService.saveAlbum(album);
        return "New album is added.";
    }

    


    /* TODO: Implement the necesseary methods:
     * create album by inserting as many songs as necesseary, create the songs during this
     * delete an album, delete all connected songs as well
     * update an album, its title, songs, song details, delete song if necesseary
     * view an album, get its tracklist, artist, total duration, count of songs*/
}
