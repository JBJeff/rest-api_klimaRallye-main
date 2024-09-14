package com.RecyclingApp.rest.webservices.restfulwebservices.jtw;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {
    
    private final JwtTokenService tokenService;
    
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(JwtTokenService tokenService, 
            AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
    System.out.println("Anmeldeversuch mit Benutzername: " + jwtTokenRequest.username());
    
    try {
        var authenticationToken = 
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(), 
                        jwtTokenRequest.password());

        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    } catch (Exception e) {
        System.out.println("Authentifizierungsfehler: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 401 zurückgeben
    }
}
}
