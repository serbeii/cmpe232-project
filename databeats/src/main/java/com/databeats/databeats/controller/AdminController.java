package com.databeats.databeats.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.Artist;
import com.databeats.databeats.model.Roles;
import com.databeats.databeats.model.UserAlbumCollectionView;
import com.databeats.databeats.repository.UserAlbumCollectionRepository;
import com.databeats.databeats.service.ArtistService;
import com.databeats.databeats.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UserAlbumCollectionRepository userAlbumCollectionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(path = "/addUser/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            userService.addUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
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
        } else {
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
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/removeArtist/{userId}")
    public ResponseEntity<?> removeArtist(@PathVariable long userId, @RequestBody String artistName) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if (artistService.removeArtistByName(artistName)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/removeUser/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody String username) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if (userService.removeUser(username)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/updateUsername/{userId}")
    public ResponseEntity<?> addUser(@PathVariable long userId, @RequestBody Map<String, String> payload) {
        String oldUsername = payload.get("oldUsername");
        String newUsername = payload.get("newUsername");

        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            if (userService.updateUsername(oldUsername, newUsername)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/view")
    public List<UserAlbumCollectionView> getUserAlbumCollection() {
        return userAlbumCollectionRepository.getUserAlbumCollection();
    }

    @DeleteMapping("/resetDatabase/{userId}")
    @Transactional
    public ResponseEntity<?> resetDatabase(@PathVariable long userId) {
        String role = userService.getRoleById(userId);
        if (role.equals("ADMIN")) {
            entityManager.createNativeQuery("DROP DATABASE cmpe232").executeUpdate();
            System.exit(0);
            return new ResponseEntity<>("Database Dropped", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }
    }

}
