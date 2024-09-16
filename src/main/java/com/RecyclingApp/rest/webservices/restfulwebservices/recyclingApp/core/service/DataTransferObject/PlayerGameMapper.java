package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject;

import org.springframework.stereotype.Component;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.PlayerGame;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PlayerGameMapper` bietet Methoden zur Konvertierung zwischen `PlayerGame`-Entitäten und `PlayerGameDTO`-Objekten.
 * Diese Klasse ermöglicht das einfache Mapping von Entitäten zu DTOs und umgekehrt, um die Daten für die API oder andere Anwendungslogik
 * aufzubereiten oder zu erstellen.
 */

 @Component
 public class PlayerGameMapper {
 
     // Konvertiert ein PlayerGame-Objekt in ein PlayerGameDTO.
      
     public PlayerGameDTO toDTO(PlayerGame playerGame) {
         if (playerGame == null) {
             return null;
         }
         return new PlayerGameDTO(
                playerGame.getId(),  
                 playerGame.getPlayer().getId(),          // ID des Spielers
                 playerGame.getGame().getId(),            // ID des Spiels
                 playerGame.getPoints(),                  // Punktestand
                 playerGame.getIsCompleted(),             // Abschlussstatus
                 playerGame.getIsSuccessful(),            // Erfolgsstatus
                 playerGame.getGame().getName()           // Name des Spiels
         );
     }
 
     //Konvertiert ein PlayerGameDTO-Objekt in ein PlayerGame-Objekt.
    
     public PlayerGame toEntity(PlayerGameDTO dto, User player, Game game) {
         if (dto == null || player == null || game == null) {
             return null;
         }
         return new PlayerGame(
                 player,
                 game,
                 dto.getPoints(),          // Punktestand
                 dto.getIsCompleted(),    // Abschlussstatus
                 dto.getIsSuccessful()    // Erfolgsstatus
         );
     }
 }