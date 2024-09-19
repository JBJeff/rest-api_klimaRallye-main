package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_info_texts")
public class GameInfoText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(nullable = false)
    private String textKey; // Schlüssel für den Informationstext (z.B. "description", "instructions")

    @Column(nullable = false)
    private String text; // Der Informationstext selbst

    @Column(nullable = true)
    private String language; // Optionale Sprachkennung, um mehrsprachige Texte zu unterstützen

    public GameInfoText() {
    }

    public GameInfoText(Game game, String textKey, String text, String language) {
        this.game = game;
        this.textKey = textKey;
        this.text = text;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}