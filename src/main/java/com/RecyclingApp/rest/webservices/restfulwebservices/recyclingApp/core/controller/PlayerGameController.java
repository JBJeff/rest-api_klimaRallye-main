package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.PlayerGameService;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.PlayerGameDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recyclingapi/player-games")
public class PlayerGameController {

    @Autowired
    private PlayerGameService playerGameService;

    @GetMapping
    public List<PlayerGame> getAllPlayerGames() {
        return playerGameService.getAllPlayerGames();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerGame>> getGamesByPlayer(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerGameService.getGamesByPlayer(playerId);
        return ResponseEntity.ok(playerGames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerGame> getPlayerGameById(@PathVariable Long id) {
        Optional<PlayerGame> playerGame = playerGameService.getPlayerGameById(id);
        return playerGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<PlayerGame> savePlayerGame(@RequestBody PlayerGame playerGame) {
        PlayerGame savedPlayerGame = playerGameService.savePlayerGame(playerGame);
        return ResponseEntity.ok(savedPlayerGame);
    }

     // Endpunkt zum Abrufen der Game ID durch die ID des PlayerGame
     @GetMapping("/{id}/game-id")
     public ResponseEntity<Long> getGameIdFromPlayerGame(@PathVariable Long id) {
         Long gameId = playerGameService.getGameIdFromPlayerGame(id);
         return (gameId != null) ? ResponseEntity.ok(gameId) : ResponseEntity.notFound().build();
     }


     //DTO gibt die Games eines Spielers zurück
     @GetMapping("/dto/{playerId}")
    public ResponseEntity<List<PlayerGameDTO>> getPlayerGamesByPlayerId(@PathVariable Long playerId) {
        List<PlayerGameDTO> playerGameDTOs = playerGameService.getPlayerGamesByPlayerId(playerId);
        if (playerGameDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerGameDTOs);
    }

    // Endpunkt zum Aktualisieren eines PlayerGame des DTO
    @PutMapping("/dto/{playerId}/{gameId}")
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