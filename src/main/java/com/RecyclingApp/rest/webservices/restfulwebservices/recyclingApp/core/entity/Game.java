package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Diese Klasse repräsentiert eine `Game`-Entität, die in einer Datenbank gespeichert wird. 
 * Die `Game`-Entität enthält Informationen wie eine eindeutige ID und einen Namen. 
 * Zudem besteht eine One-to-Many-Beziehung zu der `PlayerGame`-Entität, die die Spieler und 
 * deren Spiele verknüpft. Die Beziehung wird durch das Attribut `playerGames` abgebildet.
 */

 @Entity
 public class Game {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Inkrementierte ID
     private Long id;
 
     @Column(nullable = false, unique = true) // Der Name des Spiels muss einzigartig und darf nicht null sein
     private String name;
 
     @OneToMany(mappedBy = "game") // Eine 1:n Beziehung zu PlayerGame
     @JsonManagedReference // Verhindert rekursive Abhängigkeiten bei der JSON-Serialisierung
     private Set<PlayerGame> playerGames;
 
     // Parameterloser Konstruktor (wird für JPA benötigt)
     public Game() {}
 
     // Konstruktor mit Name-Parameter
     public Game(String name) {
         this.name = name;
     }
 
     public Long getId() {
         return id;
     }
 
     public void setId(Long id) {
         this.id = id;
     }
 
     public String getName() {
         return name;
     }
 
     public void setName(String name) {
         this.name = name;
     }
 
     // Getter und Setter für die Spiel-Zuordnungen
     public Set<PlayerGame> getPlayerGames() {
         return playerGames;
     }
 
     public void setPlayerGames(Set<PlayerGame> playerGames) {
         this.playerGames = playerGames;
     }
 }