package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Das `PlayerGameDTO` ist ein Data Transfer Object (DTO), das die relevanten Daten eines `PlayerGame`-Objekts
 * für die Eingabe oder Ausgabe bereitstellt. Es enthält Informationen über den Spieler, das Spiel, den Punktestand
 * sowie den Abschluss- und Erfolgsstatus des Spiels.
 */

public class PlayerGameDTO {

    private Long id; // ID des PlayerGame-Objekts
    private Long playerId;
    private Long gameId;
    private Integer points;
    private Boolean isCompleted;
    private Boolean isSuccessful;
    private String gameName;

    public PlayerGameDTO() {}

    public PlayerGameDTO(Long id,Long playerId, Long gameId, Integer points, Boolean isCompleted, Boolean isSuccessful, String gameName) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.points = points;
        this.isCompleted = isCompleted;
        this.isSuccessful = isSuccessful;
        this.gameName = gameName;
    }


    // Getter und Setter 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

   
}
