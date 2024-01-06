package com.databeats.databeats.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.databeats.databeats.model.Album;
import com.databeats.databeats.service.AlbumService;
/* TODO: Implement the necesseary methods:
     * create album by inserting as many songs as necesseary, create the songs during this
     * delete an album, delete all connected songs as well
     * update an album, its title, songs, song details, delete song if necesseary
     * view an album, get its tracklist, artist, total duration, count of songs*/

@RestController
@CrossOrigin
@RequestMapping("/api/v1/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    
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
    
    @PostMapping("/deleteAlbum")
    public void deleteAlbum (@RequestBody Map<String,String> albumI) {
        Long al_ID= Long.parseLong(albumI.get("album_id"));
       System.out.println(albumService.deleteAlbum(al_ID)+"Album deleted"); 
     
    }

    @PostMapping("/add")
    public String add(@RequestBody AlbumDTO albumDTO) {
        albumService.saveAlbum(albumDTO);
        return "New album is added.";
    }
}
 
   




   
