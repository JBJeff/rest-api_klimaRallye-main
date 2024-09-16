package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.PlayerService;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Der `PlayerController` ist ein REST-Controller, der Endpunkte für die Verwaltung von Spielern bereitstellt.
 * Er ermöglicht das Abrufen von Spielerinformationen und deren Spiele.
 */
@RestController
@RequestMapping("/recyclingapi/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * Gibt alle PlayerGame-Objekte für den angegebenen Spieler zurück.
     * 
     * @param playerId Die ID des Spielers, dessen Spiele abgerufen werden sollen.
     * @return Eine Liste von PlayerGame-Objekten für den angegebenen Spieler.
     */
    @GetMapping("/{playerId}/games")
    public ResponseEntity<List<PlayerGame>> getPlayerGames(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerService.findPlayerGamesByPlayerId(playerId);
        return new ResponseEntity<>(playerGames, HttpStatus.OK);
    }

    /**
     * Gibt einen Spieler anhand seines Benutzernamens zurück.
     * 
     * @param name Der Benutzername des Spielers, der abgerufen werden soll.
     * @return Die Spieler-Details, falls der Spieler gefunden wird, sonst eine "Not Found"-Antwort.
     */
    @GetMapping("/username/{name}")
    public ResponseEntity<User> getPlayerByUsername(@PathVariable String name) {
        User player = playerService.findByName(name);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }
}
