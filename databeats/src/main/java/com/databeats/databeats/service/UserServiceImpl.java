package com.databeats.databeats.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.Authentication
;
import com.databeats.databeats.controller.LoginResponse;
import com.databeats.databeats.dto.LoginDTO;
import com.databeats.databeats.dto.UserDTO;
import com.databeats.databeats.model.User;
import com.databeats.databeats.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.map(User::getUsername).orElse(null),
                                null, user.map(User::getAuthorities).orElse(null));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                    HttpSession session = request.getSession();
                    session.setAttribute("userRoles", user.map(User::getAuthorities).orElse(null));
                    return new ResponseEntity<>(login, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(login, HttpStatus.FORBIDDEN);
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

    @Override
    public String getRoleById(long userId) {
        return userRepository.findById(userId).getRoles();
    }
    
    @Override
    public boolean removeUser(String username) {
        return (userRepository.removeUser(username) > 0);
    }
    
    @Override
    public boolean updateUsername(String oldUsername, String newUsername) {
        return (userRepository.updateUsername(oldUsername, newUsername) > 0);
    }
}
