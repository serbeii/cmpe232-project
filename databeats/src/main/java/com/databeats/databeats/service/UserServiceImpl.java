package com.databeats.databeats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.databeats.databeats.config.LoginMessage;
import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.User;
import com.databeats.databeats.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public String addUser(UserDTO userDTO) {
        User user =  new User(
                userDTO.getUsername(),
                this.passwordEncoder.encode(userDTO.getPassword())
                );
        userRepository.save(user);
        return user.getUsername();
    }

    UserDTO userDTO;

    @Override
    @Transactional
    public LoginMessage loginUser(LoginDTO loginDTO) {
        User user1 = userRepository.findByUsername(loginDTO.getUsername());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            if (passwordEncoder.matches(password, encodedPassword)) {
                Optional<User> user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                }
                else {
                    return new LoginMessage("Login Failed", false);
                }
            }
            else {
                return new LoginMessage("Password does not match", false);
            }
        }
        else {
            return new LoginMessage("Username does not exist", false);
        }
    }
}
