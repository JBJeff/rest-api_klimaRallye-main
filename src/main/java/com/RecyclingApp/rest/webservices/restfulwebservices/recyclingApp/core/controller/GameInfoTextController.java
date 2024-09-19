package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.GameInfoText;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.GameInfoTextService;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `GameInfoTextController` ist ein REST-Controller, der Endpunkte für die Verwaltung von GameInfoText-Objekten bereitstellt.
 */


@RestController
@RequestMapping("/api/game-info-texts")
public class GameInfoTextController {

    @Autowired
    private GameInfoTextService gameInfoTextService;
    // Gibt eine Liste von GameInfoText-Objekten zurück, die zu einem bestimmten Spiel gehören
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<GameInfoText>> getTextsForGame(@PathVariable Long gameId,
                                                                @RequestParam(value = "language", required = false) String language) {
        List<GameInfoText> texts = (language != null) ?
            gameInfoTextService.getTextsForGame(gameId, language) :
            gameInfoTextService.getTextsForGame(gameId);
        return ResponseEntity.ok(texts);
    }

    // Speichert einen neuen Informationstext
    @PostMapping
    public ResponseEntity<Void> createGameInfoText(@RequestBody GameInfoText gameInfoText) {
        gameInfoTextService.saveGameInfoText(gameInfoText);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
