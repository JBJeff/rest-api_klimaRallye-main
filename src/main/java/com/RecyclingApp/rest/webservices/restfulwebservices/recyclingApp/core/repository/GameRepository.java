package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Das `GameRepository`-Interface ermöglicht den Zugriff auf die `Game`-Entität
 * in der Datenbank.
 * Es erweitert `JpaRepository`, wodurch Standard-CRUD-Operationen (Create,
 * Read, Update, Delete) automatisch zur Verfügung stehen.
 * Zusätzlich ist eine benutzerdefinierte Methode `findByName` definiert, um ein
 * Spiel anhand seines Namens zu finden.
 */

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Methode zur Suche eines Spiels nach seinem Namen
    Optional<Game> findByName(String name);
}
