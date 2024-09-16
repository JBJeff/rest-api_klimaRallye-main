package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.GameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerGameRepository;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.PlayerRepository;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class DataInitializer {

    private final DataSource dataSource;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerGameRepository playerGameRepository;

    @Autowired
    public DataInitializer(DataSource dataSource, PlayerRepository playerRepository,
                           GameRepository gameRepository, PlayerGameRepository playerGameRepository) {
        this.dataSource = dataSource;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.playerGameRepository = playerGameRepository;
    }

    @PostConstruct
    public void initialize() {
        try {
            // Führen Sie SQL-Skripte aus, um Daten zu initialisieren
            try (Connection connection = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("user.sql"));
                // Weitere SQL-Skripte können hier hinzugefügt werden, falls benötigt
            }
            // Verarbeitet bereits vorhandene Spieler die in der Datenbank gespeichert sind
            processExistingPlayers();
            
        } catch (SQLException e) {
            e.printStackTrace(); // 
        }
    }

    private void processExistingPlayers() {
        List<User> players = playerRepository.findAll();
        
        for (User player : players) {
            initializePlayerGames(player);
        }
    }

    private void initializePlayerGames(User player) {
        Game quizGame = getOrCreateGame("Quiz Spiel");
        Game sortingGame = getOrCreateGame("Müll Sortieren");
        Game recyclingGame = getOrCreateGame("Recyclebar oder nicht");
        Game memoryGame = getOrCreateGame("Memory");

        // Erstellen und Speichern der PlayerGame Einträge
        savePlayerGame(player, quizGame);
        savePlayerGame(player, sortingGame);
        savePlayerGame(player, recyclingGame);
        savePlayerGame(player, memoryGame);
    }

    private Game getOrCreateGame(String gameName) {
        Optional<Game> optionalGame = gameRepository.findByName(gameName);
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        } else {
            Game game = new Game(gameName);
            return gameRepository.save(game);
        }
    }

    private void savePlayerGame(User player, Game game) {
        Optional<PlayerGame> optionalPlayerGame = playerGameRepository.findByPlayerAndGame(player, game);
        if (optionalPlayerGame.isEmpty()) {
            PlayerGame playerGame = new PlayerGame(player, game, 0, false, false);
            playerGameRepository.save(playerGame);
        }
    }
}
