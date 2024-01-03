package com.databeats.databeats.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.config.LoginMessage;
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
    public String saveUser(@RequestBody UserDTO userDTO)
    {   
        System.out.println("register request");
        String role = userService.addUser(userDTO);
        return role;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        System.out.println("login request");
        String loginMessage = userService.loginUser(loginDTO);
        return ok(loginMessage);
    }
}
