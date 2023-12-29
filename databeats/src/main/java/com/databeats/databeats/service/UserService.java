package com.databeats.databeats.service;

import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.config.LoginMessage;
import com.databeats.databeats.dto.UserDTO;

public interface UserService {
   String addUser(UserDTO userDTO);
   LoginMessage loginUser(LoginDTO loginDTO);
}
