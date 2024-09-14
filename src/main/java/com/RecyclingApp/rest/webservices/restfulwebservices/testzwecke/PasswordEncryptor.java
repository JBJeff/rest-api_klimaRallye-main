package com.RecyclingApp.rest.webservices.restfulwebservices.testzwecke;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "du";  // Beispiel: Das unverschlüsselte Passwort
        String encryptedPassword = passwordEncoder.encode(plainPassword);

        System.out.println("Verschlüsseltes Passwort: " + encryptedPassword);
    }
}