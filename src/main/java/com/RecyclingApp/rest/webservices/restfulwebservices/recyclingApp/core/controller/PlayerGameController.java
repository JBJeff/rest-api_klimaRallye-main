package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.PlayerGameService;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.PlayerGameDTO;

import java.util.List;
import java.util.Optional;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PlayerGameController` ist ein REST-Controller, der Endpunkte für die Verwaltung von PlayerGame-Objekten bereitstellt.
 * Sie verwendet den `PlayerGameService`, um CRUD-Operationen auf PlayerGame-Daten durchzuführen und Daten in JSON-Format zu übermitteln.
 */
@RestController
@RequestMapping("/recyclingapi/player-games")
public class PlayerGameController {

    @Autowired
    private PlayerGameService playerGameService;

    /**
     * Gibt eine Liste aller PlayerGame-Objekte zurück.
     */
    @GetMapping
    public List<PlayerGame> getAllPlayerGames() {
        return playerGameService.getAllPlayerGames();
    }

    /**
     * Gibt alle PlayerGame-Objekte eines bestimmten Spielers zurück.
     */
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerGame>> getGamesByPlayer(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerGameService.getGamesByPlayer(playerId);
        return ResponseEntity.ok(playerGames);
    }

    /**
     * Gibt ein PlayerGame-Objekt anhand seiner ID zurück.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerGame> getPlayerGameById(@PathVariable Long id) {
        Optional<PlayerGame> playerGame = playerGameService.getPlayerGameById(id);
        return playerGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Speichert ein PlayerGame-Objekt.
     */
    @PostMapping
    public ResponseEntity<PlayerGame> savePlayerGame(@RequestBody PlayerGame playerGame) {
        PlayerGame savedPlayerGame = playerGameService.savePlayerGame(playerGame);
        return ResponseEntity.ok(savedPlayerGame);
    }

    /**
     * Gibt die Game-ID eines PlayerGame-Objekts anhand seiner ID zurück.
     */
    @GetMapping("/{id}/game-id")
    public ResponseEntity<Long> getGameIdFromPlayerGame(@PathVariable Long id) {
        Long gameId = playerGameService.getGameIdFromPlayerGame(id);
        return (gameId != null) ? ResponseEntity.ok(gameId) : ResponseEntity.notFound().build();
    }

    /**
     * Gibt die Spiele eines bestimmten Spielers als DTOs zurück.
     */
    @GetMapping("/dto/{playerId}")
    public ResponseEntity<List<PlayerGameDTO>> getPlayerGamesByPlayerId(@PathVariable Long playerId) {
        List<PlayerGameDTO> playerGameDTOs = playerGameService.getPlayerGamesByPlayerId(playerId);
        if (playerGameDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerGameDTOs);
    }

    //ruft ein explizites PlayerGame-Objekt anhand seiner ID ab und gibt das DTO zurück
    @GetMapping("/dto/{playerId}/{gameId}")
    public ResponseEntity<PlayerGameDTO> getPlayerGameById(
            @PathVariable Long playerId,
            @PathVariable Long gameId) {
        
        PlayerGameDTO playerGameDTO = playerGameService.getPlayerGameById(playerId, gameId);
        
        if (playerGameDTO != null) {
            return ResponseEntity.ok(playerGameDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Aktualisiert ein PlayerGame-Objekt und gibt das aktualisierte DTO zurück.
     
    @PutMapping("/dto/update/{playerId}/{gameId}")
    public ResponseEntity<PlayerGameDTO> updatePlayerGame(
            @PathVariable Long playerId,
            @PathVariable Long gameId,
            @RequestBody PlayerGameDTO playerGameDTO) {
        
        PlayerGameDTO updatedGame = playerGameService.updatePlayerGame(playerId, gameId, playerGameDTO);
        
        if (updatedGame == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame);
    }
}