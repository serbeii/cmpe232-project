package com.databeats.databeats.service;

import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;

public interface UserService {
   String addUser(UserDTO userDTO);
   String loginUser(LoginDTO loginDTO);
}
