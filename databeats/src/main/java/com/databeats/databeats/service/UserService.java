package com.databeats.databeats.service;



import org.springframework.http.ResponseEntity;
import java.util.*;

import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.Collection;

public interface UserService {
   String addUser(UserDTO userDTO);
   ResponseEntity<?> loginUser(LoginDTO loginDTO);  
   void addAlbumtoCollection(long userId, String albumTitle);
   void deleteAlbumFromCollection(long userId, String albumTitle);
   void deleteEntireCollection(Optional<Long> userId);
  // List<Collection> viewArtistDiscographyInCollection(String artistName);
}
