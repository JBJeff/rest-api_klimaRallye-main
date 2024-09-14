package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.GameRepository;

import java.util.List;
import java.util.Optional;

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
     
    // Spiel nach Namen finden (optional)
     public Optional<Game> getGameByName(String name) {
        return gameRepository.findByName(name);
    }
}
