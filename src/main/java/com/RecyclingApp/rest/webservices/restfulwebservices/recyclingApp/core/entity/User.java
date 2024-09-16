package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `User` repräsentiert einen Benutzer der Anwendung und wird in der
 * Datenbank unter der Tabelle "Users" gespeichert.
 * Sie enthält Informationen wie den Namen, die E-Mail-Adresse und das Passwort
 * des Benutzers.
 * Es besteht eine One-to-Many-Beziehung zu der `PlayerGame`-Entität, die die
 * Spiele des Benutzers abbildet.
 */

@Entity
@Table(name = "Users") // Definiert die Tabellennamen in der Datenbank
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Inkrementierte ID
    private Long id;

    @Column(nullable = false) // Der Name des Users darf nicht null sein
    private String name;

    @Column(nullable = false, unique = true) // E-Mail muss einzigartig und darf nicht null sein
    private String email;

    @Column(nullable = false) // Passwort darf nicht null sein
    private String password;

    @OneToMany(mappedBy = "player") // One-to-Many-Beziehung zu PlayerGame
    @JsonManagedReference // Steuert die Serialisierung, um rekursive Abhängigkeiten zu vermeiden
    private Set<PlayerGame> playerGames;

    // Parameterloser Konstruktor (für JPA erforderlich)
    public User() {
    }

    // Konstruktor mit Parametern (für die Initialisierung)
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PlayerGame> getPlayerGames() {
        return playerGames;
    }

    public void setPlayerGames(Set<PlayerGame> playerGames) {
        this.playerGames = playerGames;
    }
}