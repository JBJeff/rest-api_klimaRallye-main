package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Das `PlayerRepository`-Interface ermöglicht den Zugriff auf die `User`-Entität in der Datenbank. 
 * Es erweitert `JpaRepository`, wodurch Standard-CRUD-Operationen auf der `User`-Entität zur Verfügung stehen. 
 * Zusätzlich gibt es Methoden zur Suche von Benutzern anhand ihrer E-Mail-Adresse und ihres Namens.
 */
@Repository
public interface PlayerRepository extends JpaRepository<User, Long> {

    //Sucht einen Benutzer anhand seiner E-Mail-Adresse.
    User findByEmail(String email);
    
    //Sucht einen Benutzer anhand seines Namens.
    User findByName(String name);

}
