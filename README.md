# Spring Boot REST API

Dies ist eine Spring Boot-basierte REST API-Anwendung, die verschiedene Endpunkte zur Verfügung stellt, um Daten zu verarbeiten. Die API nutzt Spring Boot, Spring Data JPA, und Spring Security, um sichere und zuverlässige Web-Services anzubieten.

## Inhaltsverzeichnis

- [Voraussetzungen]
  
- [Installation]

- [Konfiguration]
  
- [Verwendung]

- [API-Endpunkte]

- [Sicherheit]

- [Technologien]

## Voraussetzungen

Um das Projekt lokal auszuführen, benötigst du folgende Software:
- Java 17+
- Maven 3.6+
- Eine Datenbank ( H2 für lokale Tests)

## Installation
Klone das Repository auf deinen lokalen Rechner:
   ```bash
   git clone https://github.com/dein-benutzername/dein-repository.git](https://github.com/JBJeff/rest-api_klimaRallye-main.git)
Geh in das Projektverzeichnis falls du es bereits nicht bist.
  1. cd rest-api_klimaRallye-main
  2. mvn clean install
  3. mvn spring-boot:run

## Konfiguration
Die Konfigurationsdateien befinden sich im Verzeichnis src/main/resources.
application.properties

## Verwendung 
Nach dem Starten der Anwendung kannst du auf die API zugreifen. Die Anwendung läuft standardmäßig auf http://localhost:8080.
API-Endpunkte
Die REST API bietet folgende Endpunkte:

/recyclingapi/player-games
  Beschreibung: Gibt die Spiele eines bestimmten Spielers als DTOs zurück.
  GET /recyclingapi/player-games/dto/{playerId}
  Beschreibung: ruft ein explizites PlayerGame-Objekt anhand seiner ID ab und gibt das DTO zurück
  Get /recyclingapi/player-games/dto/{playerId}/{gameId}
  Beschreibung: Aktualisiert ein PlayerGame-Objekt und gibt das aktualisierte DTO zurück.
  Put /recyclingapi/player-games/dto/update/{playerId}/{gameId}

## Sicherheit
Die Anwendung verwendet Spring Security zur Authentifizierung und Autorisierung. Um auf geschützte Endpunkte zuzugreifen, benötigst du ein Token.

JWT Token: Die API verwendet JSON Web Tokens (JWT) für die Authentifizierung.
curl -X POST http://localhost:8080/auth -H "Content-Type: application/json" -d '{"username": "user", "email ": "jb@t-online.de" ,password": "pass"}'

##Technologien
Das Projekt basiert auf folgenden Technologien:

Java 17: Hauptprogrammiersprache.
Spring Boot: Framework für die Anwendungsentwicklung.
Spring Data JPA: Datenbankzugriffe und ORM.
Spring Security: Authentifizierung und Autorisierung.
Maven: Build-Management.




