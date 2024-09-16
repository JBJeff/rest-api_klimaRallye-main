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

@Entity
@Table(name = "Users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "player")
    @JsonManagedReference
    private Set<PlayerGame> playerGames;

    // Parameterloser Konstruktor
    public User() {}
    
    // Konstruktor mit Parametern
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter und Setter
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

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public String getUsername() {
    //     return email;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }
}