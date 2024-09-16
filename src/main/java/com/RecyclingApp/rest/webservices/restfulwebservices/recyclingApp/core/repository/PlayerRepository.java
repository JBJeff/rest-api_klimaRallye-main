package com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity.User;

@Repository
public interface PlayerRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
    User findByName(String name);

}
