package com.databeats.databeats.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.model.Roles;
import com.databeats.databeats.service.ArtistService;
import com.databeats.databeats.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin")
public class AdminController {
    /*TODO: Implement the following methods:
     * advanced user search,
     * database nuke(drop and exit app)*/
    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    @PostMapping(path = "/addUser/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            userService.addUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping(path = "/addAdmin/{userId}")
    public ResponseEntity<?> addAdmin(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            userDTO.setRole(Roles.ADMIN);
            userService.addUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/addArtist/{userId}")
    public ResponseEntity<?> addArtist(@PathVariable long userId, @RequestBody String artistName) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            Artist artist = new Artist(artistName);
            artistService.addArtist(artist);
            return new ResponseEntity<>(HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/removeArtist/{userId}")
    public ResponseEntity<?> removeArtist(@PathVariable long userId, @RequestBody String artistName) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if (artistService.removeArtistByName(artistName)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping(path = "/removeUser/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody String username) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if (userService.removeUser(username)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/updateUsername/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody Map<String, String> payload) {
        String oldUsername = payload.get("oldUsername");
        String newUsername = payload.get("newUsername");

        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if(userService.updateUsername(oldUsername, newUsername)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }
    
}
