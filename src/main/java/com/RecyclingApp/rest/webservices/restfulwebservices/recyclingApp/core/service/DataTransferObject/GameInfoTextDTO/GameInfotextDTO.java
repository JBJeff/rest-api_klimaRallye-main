package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.DataTransferObject.GameInfoTextDTO;

public class GameInfotextDTO {

    private Long gameId;
    private String textKey;
    private String text;
    private String language;

    // Standard-Konstruktor
    public GameInfotextDTO() {
    }

    // Konstruktor
    public GameInfotextDTO(Long gameId, String textKey, String text, String language) {
        this.gameId = gameId;
        this.textKey = textKey;
        this.text = text;
        this.language = language;
    }

    // Getter und Setter
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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