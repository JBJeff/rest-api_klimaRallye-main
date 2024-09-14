package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByEmail(String email);
    
    Player findByName(String name);

}
