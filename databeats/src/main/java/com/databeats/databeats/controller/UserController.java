package com.databeats.databeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.databeats.databeats.service.UserService;
import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.dto.CollectionDTO;
import com.databeats.databeats.model.Collection;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    /*TODO: implement necesseary methods
     * add and remove albums from collection
     * delete entire collection
     * change username and force logout
     * view artist discography*/
    @Autowired
    private UserService userService;

    @PostMapping ("/addAlbumtoCollection")
    public void addAlbumtoCollection(@RequestBody Map<String,String> payload)
    {
        System.out.println("succesful");
        Long userId = Long.parseLong(payload.get("userId"));
        String albumTitle = payload.get("albumTitle");

        userService.addAlbumtoCollection(userId, albumTitle);
    }

    @PostMapping ("/deleteAlbumFromCollection")
    public void deleteAlbumFromCollection(@RequestBody Map<String,String> payload){
        System.out.println("succesful");
        Long userId = Long.parseLong(payload.get("userId"));
        String albumTitle = payload.get("albumTitle");

        userService.deleteAlbumFromCollection(userId, albumTitle);
    }

    @PostMapping ("/deleteEntireCollection")
     public void deleteEntireCollection(Optional<Long> userId){
        userService.deleteEntireCollection(userId);
    }
    
    @GetMapping ("/viewCollection/{user_id}")
    public List<CollectionDTO> getUserCollection(@PathVariable long user_id) {
        return userService.getCollection(user_id);
    }

    @PostMapping ("/viewArtistDiscographyInCollection")
    public List<AlbumDTO> viewArtistDiscographyInCollection(@RequestParam String artistName){
        return(userService.viewArtistDiscographyInCollection(artistName));
    }


}
