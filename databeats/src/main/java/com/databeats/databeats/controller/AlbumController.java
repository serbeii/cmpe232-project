package com.databeats.databeats.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.dto.AlbumBody;
import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.dto.SongDTO;
import com.databeats.databeats.model.Album;
import com.databeats.databeats.service.AlbumService;
import com.databeats.databeats.service.SongService;

    /* TODO: Implement the necesseary methods:
     * delete an album, delete all connected songs as well
     * update an album, its title, songs, song details, delete song if necesseary
     * view an album, get its tracklist, artist, total duration, count of songs*/

@RestController
@CrossOrigin
@RequestMapping("/api/v1/album")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;
    
    @GetMapping("/getAlbumTitle")
    public String updateAlbumTitle (@RequestBody Map<String, String > oldTitle) {
        String oldtitle = oldTitle.get("oldTitle");
        String newtitle = oldTitle.get("newTitle");
       albumService.updateAlbumTitle(oldtitle, newtitle);
        return "Updated succesfully! Your new title is "+ newtitle;
    }
    
    @GetMapping("/getAlbumInfo") 
    public List<Album> getAlbumInfo(@RequestBody Map<String,String> albumID) {
        Long albumid= Long.parseLong(albumID.get("albumID"));
        return albumService.getAlbumInfo(albumid);
        
        
    }
    
    @PostMapping("/deleteAlbum")//when I want to delete something it deletes except one spesific information when ı tried to delete ı got error 500
    public void deleteAlbum (@RequestBody Map<String,String> albumI) {
        Long al_ID= Long.parseLong(albumI.get("album_id"));
        System.out.println(albumService.deleteAlbum(al_ID)+"Album deleted"); 
     
    }

    @GetMapping("/searchAlbum/{substring}")
    public List<AlbumDTO> searchAlbum(@PathVariable String substring) { 
        return albumService.searchAlbum(substring);
    }

    @GetMapping("/viewAlbum/{album_name}")
    public AlbumBody viewAlbum(@PathVariable String album_name) {
        return albumService.viewAlbum(album_name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AlbumBody albumBody) {
        if (albumBody == null || albumBody.getAlbumDTO() == null || albumBody.getSongDTO() == null) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }
        AlbumDTO albumDTO = albumBody.getAlbumDTO();
        List<SongDTO> songDTO = albumBody.getSongDTO(); 
        albumService.saveAlbum(albumDTO);
        songService.addSongs(songDTO, albumDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
} 
