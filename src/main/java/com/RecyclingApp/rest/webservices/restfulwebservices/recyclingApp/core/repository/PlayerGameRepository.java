package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;

import java.util.List;
import java.util.Optional;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Das `PlayerGameRepository`-Interface ermöglicht den Zugriff auf die `PlayerGame`-Entität in der Datenbank. 
 * Es erweitert `JpaRepository`, wodurch Standard-CRUD-Operationen zur Verfügung stehen. Zusätzlich gibt es Methoden, 
 * die nach `PlayerGame`-Entitäten basierend auf Spieler- und Spiel-IDs suchen, sowie eine benutzerdefinierte JPQL-Abfrage zur Suche nach Spieler-IDs.
 */
@Repository
public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long> {

    //List<PlayerGame> findByPlayerId(Long playerId);

    //Sucht ein PlayerGame anhand der Spieler-ID und der Spiel-ID.
    Optional<PlayerGame> findByPlayerIdAndGameId(Long playerId, Long gameId);

    //Sucht ein PlayerGame anhand eines Spieler-Objekts und eines Spiel-Objekts.
    Optional<PlayerGame> findByPlayerAndGame(User player, Game game);

    //PlayerGame nach seiner ID zu finden
    Optional<PlayerGame> findById(Long id);

    //Benutzerdefinierte JPQL-Abfrage, um alle PlayerGames zu finden, die einem Spieler zugeordnet sind.
    @Query("SELECT pg FROM PlayerGame pg WHERE pg.player.id = :playerId")
    List<PlayerGame> findByPlayerId(@Param("playerId") Long playerId);
}
