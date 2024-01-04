package com.databeats.databeats.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.databeats.databeats.controller.LoginResponse;
import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.User;
import com.databeats.databeats.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public String addUser(UserDTO userDTO) {
        userRepository.addUser(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getRole());
        return userDTO.getRole();
    }

    @Override
    @Transactional
    public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
        User user1 = userRepository.findByUsername(loginDTO.getUsername());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            if (passwordEncoder.matches(password, encodedPassword)) {
                Optional<User> user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
                LoginResponse login = new LoginResponse(user.map(User::getRoles).orElse(null),
                        user.map(User::getUserId).orElse(null));
                if (user.isPresent()) {
                    return new ResponseEntity<>(login, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(login, HttpStatus.NOT_FOUND);
                }
            }
            else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostConstruct
    public void generateDefaultAdmin() {
        try {
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            userRepository.addUser("admin", passwordEncoder.encode("adminpass"), "ADMIN");
            System.out.println("creating default admin");
        }
        }
        catch (Exception e)
        {
            System.out.println("Error during initialization " + e);
        }
    }
}
