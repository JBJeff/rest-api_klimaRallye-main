package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject;

import org.springframework.stereotype.Component;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PlayerGameConverter` konvertiert eine `PlayerGame`-Entität in ein `PlayerGameDTO`-Objekt. 
 * Diese Konvertierung ist nützlich, um Daten für die API-Ausgabe oder andere Anwendungslogik zu formatieren.
 */
@Component
public class PlayerGameConverter {

    //Konvertiert ein PlayerGame-Objekt in ein PlayerGameDTO.
    public PlayerGameDTO convertToDTO(PlayerGame playerGame) {
        if (playerGame == null) {
            return null;
        }
        return new PlayerGameDTO(
            playerGame.getPlayer().getId(),          // ID des Spielers
            playerGame.getGame().getId(),            // ID des Spiels
            playerGame.getPoints(),                  // Punktestand
            playerGame.getIsCompleted(),             // Abschlussstatus
            playerGame.getIsSuccessful(),            // Erfolgsstatus
            playerGame.getGame().getName()           // Name des Spiels
            
        );
    }

        
}
