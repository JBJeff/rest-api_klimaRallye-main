package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PlayerGame` repräsentiert die Verknüpfung zwischen einem Spieler
 * (User) und einem Spiel (Game).
 * Diese Entität speichert Informationen über den Punktestand, den
 * Abschlussstatus und den Erfolg des Spiels
 * für jeden Spieler. Die Verbindungen zu den Entitäten `User` und `Game` sind
 * durch Many-to-One-Beziehungen definiert.
 */

@Entity
public class PlayerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Inkrementierte ID
    private Long id;

    @ManyToOne // Viele PlayerGame-Einträge beziehen sich auf einen User
    @JoinColumn(name = "user_id", nullable = false) // Verknüpfung der Spalte mit der User-Tabelle
    @JsonBackReference // Verhindert rekursive Abhängigkeiten bei der JSON-Serialisierung
    private User player;

    @ManyToOne // Viele PlayerGame-Einträge beziehen sich auf ein Game
    @JoinColumn(name = "game_id", nullable = false) // Verknüpfung der Spalte mit der Game-Tabelle
    @JsonBackReference // Verhindert rekursive Abhängigkeiten bei der JSON-Serialisierung
    private Game game;

    @Column(nullable = true) //  Feld für den Punktestand
    private Integer points;

    @Column(nullable = true) // Feld um zu speichern ob das Spiel abgeschlossen ist
    private Boolean isCompleted;

    @Column(nullable = true) //Feld um zu speichern ob das Spiel erfolgreich war
    private Boolean isSuccessful;

    // Parameterloser Konstruktor (erforderlich für JPA)
    public PlayerGame() {
    }

    // Konstruktor mit Parametern
    public PlayerGame(User player, Game game, Integer points, Boolean isCompleted, Boolean isSuccessful) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.isCompleted = isCompleted;
        this.isSuccessful = isSuccessful;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    // Getter und Setter für isCompleted (Abschlussstatus)
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    // Getter und Setter für isSuccessful (Erfolgsstatus)
    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}