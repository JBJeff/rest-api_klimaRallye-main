package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Player;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerGameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerGame> findPlayerGamesByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player findPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }
}
