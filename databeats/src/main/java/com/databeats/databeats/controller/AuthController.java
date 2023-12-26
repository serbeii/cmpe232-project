package com.databeats.databeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import com.databeats.databeats.model.User;
import com.databeats.databeats.service.UserService;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Authenticate user
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

            // Manually authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), user.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // You can do additional logic here if needed

            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            return ResponseEntity.ok(Collections.singletonMap("role", role));
        } catch (Exception e) {
            // Handle exceptions (e.g., BadCredentialsException, LockedException) appropriately
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
