package com.RecyclingApp.rest.webservices.restfulwebservices.jtw;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.service.CustomPlayerDetailsService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung:
 * Die Klasse `JwtSecurityConfig` konfiguriert die Sicherheitsaspekte der
 * Anwendung unter Verwendung von JWT (JSON Web Tokens).
 * Sie definiert die Sicherheitsfilter, Passwortverschlüsselung,
 * Authentifizierung und JWT-Management.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityConfig {

    @Autowired
    private CustomPlayerDetailsService customPlayerDetailsService;

    /**
     * Konfiguriert die HTTP-Sicherheitsfilter der Anwendung.
     * - Deaktiviert die Authentifizierung für alle Endpunkte. Muss geändert werden,
     * um die Authentifizierung zu aktivieren.
     * - Deaktiviert CSRF-Schutz.
     * - Setzt die Session-Management-Politik auf stateless.
     * - Aktiviert OAuth2-Ressourcen-Server mit JWT.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Deaktivieren der Authentifizierung für alle Endpunkte
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/**").permitAll()) // Alle Pfade sind ohne Authentifizierung zugänglich
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(Customizer.withDefaults())
                .headers(header -> header
                        .frameOptions().sameOrigin());

        return httpSecurity.build();
    }

    // Bean für den UserDetailsService, der Benutzerinformationen bereitstellt.
    @Bean
    public UserDetailsService userDetailsService() {
        return customPlayerDetailsService;
    }

    // Bean für den Passwort-Encoder zur Verschlüsselung von Passwörtern.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean für den AuthenticationManager zur Authentifizierung von Benutzern.
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Passwort-Encoder setzen
        return new ProviderManager(authenticationProvider);
    }

    // Bean für die JWK-Source zur Erzeugung von JWT.
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(rsaKey());
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    // Bean für den JwtEncoder zur Erstellung von JWTs.
    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    // Bean für den JwtDecoder zur Verifizierung und Decodierung von JWTs.
    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey().toRSAPublicKey())
                .build();
    }

    // Bean für den RSA-Schlüssel, der für die JWT-Signierung verwendet wird.
    @Bean
    public RSAKey rsaKey() {
        KeyPair keyPair = keyPair();
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    // Bean für das RSA-Key-Paar.
    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to generate an RSA Key Pair", e);
        }
    }

}
