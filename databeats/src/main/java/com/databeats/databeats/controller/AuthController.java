package com.databeats.databeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping(path = "/register")
    public ResponseEntity<LoginResponse> saveUser(@RequestBody UserDTO userDTO)
    {   
        String role = userService.addUser(userDTO);
        long id = userDTO.getUserId();
        
        LoginResponse loginState = new LoginResponse(role, id);

        return new ResponseEntity<>(loginState, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        return userService.loginUser(loginDTO);
    }
}
