package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject;

import org.springframework.stereotype.Component;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;

@Component
public class PlayerGameConverter {

    public PlayerGameDTO convertToDTO(PlayerGame playerGame) {
        if (playerGame == null) {
            return null;
        }
        return new PlayerGameDTO(
            playerGame.getPlayer().getId(),
            playerGame.getGame().getId(),
            playerGame.getPoints(),
            playerGame.getIsCompleted(),
            playerGame.getIsSuccessful(),
            playerGame.getGame().getName()
            
        );
    }

        
}
