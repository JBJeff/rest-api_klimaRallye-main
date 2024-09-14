package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Player;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.PlayerService;

@RestController
@RequestMapping("/recyclingapi/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}/games")
    public ResponseEntity<List<PlayerGame>> getPlayerGames(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerService.findPlayerGamesByPlayerId(playerId);
        return new ResponseEntity<>(playerGames, HttpStatus.OK);
    }

    @GetMapping("/username/{name}")
    public ResponseEntity<Player> getPlayerByUsername(@PathVariable String name) {
        Player player = playerService.findByName(name);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }
}
