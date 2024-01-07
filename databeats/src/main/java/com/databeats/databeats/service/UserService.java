package com.databeats.databeats.service;



import org.springframework.http.ResponseEntity;
import java.util.*;

import com.databeats.databeats.dto.AlbumDTO;
import com.databeats.databeats.dto.CollectionDTO;
import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;

public interface UserService {
    String addUser(UserDTO userDTO);
    ResponseEntity<?> loginUser(LoginDTO loginDTO);  
    void addAlbumtoCollection(long userId, String albumTitle);
    void deleteAlbumFromCollection(long userId, String albumTitle);
    void deleteEntireCollection(Optional<Long> userId);
    List<AlbumDTO> viewArtistDiscographyInCollection(String artistName);
    String getRoleById(long userId);
    boolean removeUser(String username);
    boolean updateUsername(String oldUsername, String newUsername);
    List<CollectionDTO> getCollection(long user_id);
    String getUsername(long user_id);
}
