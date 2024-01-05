package com.databeats.databeats.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.model.Artist;
import com.databeats.databeats.service.ArtistService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/getArtistByName")
    public Artist getArtistByName(@RequestParam String artistName) {
        return artistService.getArtistByName(artistName);
    }
}
