package com.RecyclingApp.rest.webservices.restfulwebservices.jtw;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `JwtTokenService` ist ein Service, der JWTs (JSON Web Tokens) erstellt.
 * Sie verwendet einen `JwtEncoder`, um den Token zu generieren, der Authentifizierungsinformationen und
 * Berechtigungen enthält.
 */
@Service
public class JwtTokenService {
    
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    //Generiert einen JWT-Token basierend auf den Authentifizierungsdetails des Benutzers.
    public String generateToken(Authentication authentication) {

        // Berechtigungen des Benutzers in einem einzelnen String zusammenfassen
        var scope = authentication
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));

        // Erstellen der JWT-Anspruchsgruppe
        var claims = JwtClaimsSet.builder()
                        .issuer("self")  // Angabe des Token-Ausstellers
                        .issuedAt(Instant.now())  // Zeitpunkt der Token-Erstellung
                        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))  // Ablaufdatum des Tokens
                        .subject(authentication.getName())  // Betreff des Tokens, typischerweise der Benutzername
                        .claim("scope", scope)  // Benutzerberechtigungen als benutzerdefinierter Anspruch
                        .build();

        // Token erstellen und zurückgeben
        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))  // JWT mit den Anspruchsdaten erstellen
                .getTokenValue();  // Token-Wert als String zurückgeben
    }
}