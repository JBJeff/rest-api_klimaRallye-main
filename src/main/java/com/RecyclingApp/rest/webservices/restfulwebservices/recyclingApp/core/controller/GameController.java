package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Der `GameController` ist ein REST-Controller, der Endpunkte für die Verwaltung von Spielen bereitstellt.
 * Er ermöglicht das Abrufen, Speichern und Suchen von Spielen.
 */
@RestController
@RequestMapping("/recyclingapi/api/games")
public class GameController {

    @Autowired
    private com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.GameService gameService;

    //Gibt alle Spiele zurück.
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    //Gibt ein Spiel anhand seiner ID zurück.
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Game game = gameService.findGameById(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    //Speichert oder aktualisiert ein Spiel. Unnötig
    @PostMapping
    public ResponseEntity<Game> UpdateGame(@RequestBody Game game) {
        Game savedGame = gameService.saveGame(game);
        return ResponseEntity.ok(savedGame);
    }

    //Gibt ein Spiel anhand seines Namens zurück.
    @GetMapping("/search")
    public ResponseEntity<Game> getGameByName(@RequestParam String name) {
        Optional<Game> game = gameService.getGameByName(name);
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
