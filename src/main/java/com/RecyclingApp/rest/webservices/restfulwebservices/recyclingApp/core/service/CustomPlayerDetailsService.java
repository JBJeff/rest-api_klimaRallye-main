package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerRepository;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `CustomPlayerDetailsService` implementiert den `UserDetailsService`-Interface von Spring Security,
 * um benutzerdefinierte Authentifizierungslogik für Benutzer bereitzustellen. Sie wird verwendet, um Benutzerdetails
 * aus der Datenbank zu laden, basierend auf dem Benutzernamen, der beim Anmeldeprozess angegeben wird.
 */

@Service
public class CustomPlayerDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    //Lädt die Benutzerdetails basierend auf dem Benutzernamen.
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