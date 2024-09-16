package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerGameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerRepository;

import java.util.List;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Der Service `PlayerService` verwaltet die Geschäftslogik für Spieler. Er verwendet die Repositories 
 * `PlayerRepository` und `PlayerGameRepository` zur Durchführung von Operationen auf Spielerobjekten 
 * und deren zugehörigen Spielständen.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerGameRepository playerGameRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Gibt eine Liste von PlayerGame-Objekten zurück, die mit einem bestimmten Spieler verknüpft sind.
    public List<PlayerGame> findPlayerGamesByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    // Speichert ein Spieler-Objekt und gibt das gespeicherte Objekt zurück.
    public User savePlayer(User player) {
        return playerRepository.save(player);
    }

    // Gibt ein Spieler-Objekt mit der angegebenen ID zurück.
    public User findPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    // Gibt ein Spieler-Objekt mit dem angegebenen Namen zurück.
    public User findByName(String name) {
        return playerRepository.findByName(name);
    }

    // Gibt die ID des Spiels eines bestimmten PlayerGame-Objekts zurück.
    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }
}