package com.databeats.databeats.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sequence
    private long userId;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null) {
            return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
        } else {
            return Collections.singleton(new SimpleGrantedAuthority("USER"));
        }
    }

    public User() {}
    
    public User(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        try {
            this.role = Enum.valueOf(Roles.class, role);
        }
        catch (IllegalArgumentException e) {
            this.role = Roles.USER;
        }
    }

    public String getRoles() {
        return role.toString();
    }

    public long getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
