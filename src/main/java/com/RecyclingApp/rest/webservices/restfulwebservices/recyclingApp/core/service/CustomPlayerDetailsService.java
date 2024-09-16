package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerRepository;

@Service
public class CustomPlayerDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User player = playerRepository.findByName(name);
        if (player == null) {
            throw new UsernameNotFoundException("User not found with email: " + name);
        }
        return new org.springframework.security.core.userdetails.User(
            player.getEmail(),
            player.getPassword(),
            true, // accountNonExpired
            true, // credentialsNonExpired
            true, // accountNonLocked
            true, // enabled
            new ArrayList<>() // authorities
        );
    }
}