package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject;

import org.springframework.stereotype.Component;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Player;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;

@Component
public class PlayerGameMapper {

    // PlayerGame zu PlayerGameDTO konvertieren
    public PlayerGameDTO toDTO(PlayerGame playerGame) {
        return new PlayerGameDTO(
                playerGame.getPlayer().getId(),
                playerGame.getGame().getId(),
                playerGame.getPoints(),
                playerGame.getIsCompleted(),
                playerGame.getIsSuccessful(),
                playerGame.getGame().getName()
        );
    }

    // PlayerGameDTO zu PlayerGame konvertieren
    public PlayerGame toEntity(PlayerGameDTO dto, Player player, Game game) {
        return new PlayerGame(
                player,
                game,
                dto.getPoints(),
                dto.getIsCompleted(),
                dto.getIsSuccessful()
        );
    }
}