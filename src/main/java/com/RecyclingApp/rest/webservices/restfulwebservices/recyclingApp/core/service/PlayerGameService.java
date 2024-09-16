package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.GameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerGameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.PlayerGameConverter;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.PlayerGameDTO;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.PlayerGameMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PlayerGameService` ist ein Service, der Geschäftslogik für die Verwaltung von PlayerGame-Objekten bereitstellt.
 * Sie verwendet die Repositories `GameRepository` und `PlayerGameRepository` sowie Konverter- und Mapper-Klassen, um CRUD-Operationen
 * durchzuführen und DTOs für die Übertragung von Daten bereitzustellen.
 */
@Service
public class PlayerGameService {

    private final GameRepository gameRepository;

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    private PlayerGameConverter playerGameConverter;

    @Autowired
    private PlayerGameMapper playerGameMapper;

    public PlayerGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Gibt eine Liste von PlayerGameDTO zurück, die alle Spiele eines bestimmten Spielers darstellen.
    public List<PlayerGameDTO> getPlayerGamesByPlayerId(Long playerId) {
        List<PlayerGame> playerGames = playerGameRepository.findByPlayerId(playerId);
        if (playerGames == null || playerGames.isEmpty()) {
            return Collections.emptyList(); // wirft eine benutzerdefinierte Ausnahme
        }

        // Konvertiert die Liste von PlayerGame in eine Liste von PlayerGameDTO
        return playerGames.stream()
                .map(playerGameConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    //Aktualisiert ein PlayerGame-Objekt und gibt das aktualisierte DTO zurück.
    public PlayerGameDTO updatePlayerGame(Long playerId, Long gameId, PlayerGameDTO playerGameDTO) {
        Optional<PlayerGame> optionalPlayerGame = playerGameRepository.findByPlayerIdAndGameId(playerId, gameId);
        
        if (optionalPlayerGame.isPresent()) {
            PlayerGame playerGame = optionalPlayerGame.get();
            playerGame.setPoints(playerGameDTO.getPoints());
            playerGame.setIsCompleted(playerGameDTO.getIsCompleted());
            playerGame.setIsSuccessful(playerGameDTO.getIsSuccessful());

            PlayerGame updatedGame = playerGameRepository.save(playerGame);
            return playerGameMapper.toDTO(updatedGame);
        } else {
            return null;  // Spiel nicht gefunden
        }
    }  

    //Gibt eine Liste aller PlayerGame-Objekte zurück. 
    public List<PlayerGame> getAllPlayerGames() {
        return playerGameRepository.findAll();
    }

    //Gibt eine Liste von PlayerGame-Objekten für einen bestimmten Spieler zurück.
    public List<PlayerGame> getGamesByPlayer(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    //Gibt ein Optional mit einem PlayerGame zurück, das die angegebene ID hat.  
    public Optional<PlayerGame> getPlayerGameById(Long id) {
        return playerGameRepository.findById(id);
    }
    
    //Speichert ein PlayerGame-Objekt und gibt das gespeicherte Objekt zurück.
    public PlayerGame savePlayerGame(PlayerGame playerGame) {
        return playerGameRepository.save(playerGame);
    }

    //Gibt eine Liste von PlayerGame-Objekten für den angegebenen Spieler zurück.
     
    public List<PlayerGame> findByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    //Gibt die game_id eines PlayerGame-Objekts anhand seiner ID zurück.
    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }

     // public PlayerGame findPlayerGameByPlayerIdAndGameId(Long playerId, Long gameId) {
    //     return playerGameRepository.findByPlayerIdAndGameId(playerId, gameId);
    // }

}

