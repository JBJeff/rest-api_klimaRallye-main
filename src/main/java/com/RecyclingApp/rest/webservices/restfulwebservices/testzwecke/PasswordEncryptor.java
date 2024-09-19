package com.RecyclingApp.rest.webservices.restfulwebservices.testzwecke;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `PasswordEncryptor` dient zur Verschlüsselung von Passwörtern mittels der BCrypt-Kodierung.
 * Sie enthält eine `main`-Methode, die ein Beispielpasswort verschlüsselt und das Ergebnis in der Konsole ausgibt.
 */
public class PasswordEncryptor {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "du";  // Beispiel: Das unverschlüsselte Passwort
        String encryptedPassword = passwordEncoder.encode(plainPassword);

        System.out.println("Verschlüsseltes Passwort: " + encryptedPassword);
    }
}