package com.databeats.databeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databeats.databeats.model.User;
import com.databeats.databeats.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/details")
    public String getUser(@RequestParam long userId) {
        User user = userRepository.findById(userId);
        return user.getUsername();
    }
}
