package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.GameRepository;

import java.util.List;
import java.util.Optional;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `GameService` ist ein Service, der Geschäftslogik für die Verwaltung von Spielen bereitstellt.
 * Sie verwendet das `GameRepository`, um CRUD-Operationen auf Spielobjekten durchzuführen.
 * 
 * Methoden:
 * - `findAllGames()`: Gibt eine Liste aller Spiele zurück.
 * - `findGameById(Long id)`: Findet ein Spiel anhand seiner ID.
 * - `saveGame(Game game)`: Speichert ein Spiel in der Datenbank.
 * - `getGameByName(String name)`: Findet ein Spiel anhand seines Namens (optional).
 */

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
     
     public Optional<Game> getGameByName(String name) {
        return gameRepository.findByName(name);
    }
}
