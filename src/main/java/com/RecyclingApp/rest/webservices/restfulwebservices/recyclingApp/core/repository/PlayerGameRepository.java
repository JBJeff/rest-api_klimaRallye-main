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

@Repository
public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long> {

    //List<PlayerGame> findByPlayerId(Long playerId);

    Optional<PlayerGame> findByPlayerIdAndGameId(Long playerId, Long gameId);

    Optional<PlayerGame> findByPlayerAndGame(User player, Game game);

    @Query("SELECT pg FROM PlayerGame pg WHERE pg.player.id = :playerId")
    List<PlayerGame> findByPlayerId(@Param("playerId") Long playerId);
}
