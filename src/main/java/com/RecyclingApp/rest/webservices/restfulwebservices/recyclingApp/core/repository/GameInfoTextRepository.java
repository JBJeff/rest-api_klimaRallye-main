package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Das `GameInfoTextRepository`-Interface ermöglicht den Zugriff auf die `GameInfoText`-Entität
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.GameInfoText;

public interface GameInfoTextRepository extends JpaRepository<GameInfoText, Long> {

    // Methode zur Suche von Informationstexten anhand der Spiel-ID
    List<GameInfoText> findByGameId(Long gameId);

    // Methode zur Suche von Informationstexten anhand der Spiel-ID und der Sprache
    List<GameInfoText> findByGameIdAndLanguage(Long gameId, String language);
}