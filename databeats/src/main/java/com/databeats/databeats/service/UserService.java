package com.databeats.databeats.service;

import org.springframework.http.ResponseEntity;

import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;

public interface UserService {
   String addUser(UserDTO userDTO);
   ResponseEntity<?> loginUser(LoginDTO loginDTO);
}
