package com.databeats.databeats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.databeats.databeats.model.User;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = new User("user1", passwordEncoder().encode("pass1"), "USER");
        UserDetails admin = new User("admin", passwordEncoder().encode("adminpass"), "ADMIN"); 
        return new InMemoryUserDetailsManager(user1, admin);
    }

}
