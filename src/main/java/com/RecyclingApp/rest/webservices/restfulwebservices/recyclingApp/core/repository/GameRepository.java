package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Standard-CRUD-Methoden stehen zur Verfügung
    Optional<Game> findByName(String name);

}
