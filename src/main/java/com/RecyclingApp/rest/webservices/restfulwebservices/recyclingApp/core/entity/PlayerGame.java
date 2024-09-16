package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @JsonBackReference
    private Game game;

    @Column(nullable = true)
    private Integer points;

    @Column(nullable = true)
    private Boolean isCompleted;

    @Column(nullable = true)
    private Boolean isSuccessful;

    // Parameterloser Konstruktor
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

    // Getter und Setter
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
}