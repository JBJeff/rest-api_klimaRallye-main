package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.GameInfoText;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository.GameInfoTextRepository;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `GameInfoTextService` ist ein Service, der Geschäftslogik für die Verwaltung von GameInfoText-Objekten bereitstellt.
 */
@Service
public class GameInfoTextService {
    
    // Das `GameInfoTextRepository`-Objekt wird automatisch injiziert
    @Autowired
    private GameInfoTextRepository gameInfoTextRepository;

    // Gibt eine Liste von GameInfoText-Objekten zurück, die zu einem bestimmten Spiel gehören
    public List<GameInfoText> getTextsForGame(Long gameId) {
        return gameInfoTextRepository.findByGameId(gameId);
    }

    // Gibt eine Liste von GameInfoText-Objekten zurück, die zu einem bestimmten Spiel und einer bestimmten Sprache gehören
    public List<GameInfoText> getTextsForGame(Long gameId, String language) {
        return gameInfoTextRepository.findByGameIdAndLanguage(gameId, language);
    }

    // Speichert einen neuen Informationstext
    public void saveGameInfoText(GameInfoText gameInfoText) {
        gameInfoTextRepository.save(gameInfoText);
    }

    // Gibt alle Informationstexte zurück
    public List<GameInfoText> findAllGameInfoTexts() {
        return gameInfoTextRepository.findAll();
    }

    // Löscht einen Informationstext
    public void deleteGameInfoText(Long id) {
        GameInfoText gameInfoText = gameInfoTextRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gameInfoText ID: " + id));
        gameInfoTextRepository.delete(gameInfoText);
    }
}