package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
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

@Service
public class PlayerGameService {

     private final GameRepository gameRepository; // Dein Repository für Spiele

    public PlayerGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    private PlayerGameConverter playerGameConverter; 

     @Autowired
    private PlayerGameMapper playerGameMapper; 

    // Konvertiert das PlayerGame-Objekt in ein DTO, wird von der Controller-Klasse aufgerufen
    public List<PlayerGameDTO> getPlayerGamesByPlayerId(Long playerId) {
        List<PlayerGame> playerGames = playerGameRepository.findByPlayerId(playerId);
        if (playerGames == null || playerGames.isEmpty()) {
            return Collections.emptyList(); // oder werfen Sie eine benutzerdefinierte Ausnahme
        }

        // Konvertieren Sie die Liste von PlayerGame in eine Liste von PlayerGameDTO
        return playerGames.stream()
                .map(playerGameConverter::convertToDTO)
                .collect(Collectors.toList());
    }

     // Update Methode zum Aktualisieren eines Spiels
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

    public List<PlayerGame> getAllPlayerGames() {
        return playerGameRepository.findAll();
    }

    public List<PlayerGame> getGamesByPlayer(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    public Optional<PlayerGame> getPlayerGameById(Long id) {
        return playerGameRepository.findById(id);
    }
    
    public PlayerGame savePlayerGame(PlayerGame playerGame) {
        return playerGameRepository.save(playerGame);
    }

    public List<PlayerGame> findByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    // public PlayerGame findPlayerGameByPlayerIdAndGameId(Long playerId, Long gameId) {
    //     return playerGameRepository.findByPlayerIdAndGameId(playerId, gameId);
    // }

    // Methode zum Abrufen der game_id
    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }



}
