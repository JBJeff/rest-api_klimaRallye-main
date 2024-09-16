package com.RecyclingApp.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Autor: Jeffrey Böttcher
 * Version: 1.0
 * 
 * Beschreibung: 
 * Dies ist die Hauptklasse der Spring Boot Anwendung. Die Anwendung verwendet eine CORS-Konfiguration, 
 * um den Zugriff auf die Backend-APIs von einer React-Frontend-Anwendung, die auf `http://localhost:3000` läuft, zu ermöglichen. 
 * Der CORS-Filter erlaubt alle HTTP-Methoden. Die Klasse beinhaltet zudem die Standard-`main` Methode, 
 * um die Spring Boot Anwendung zu starten.
 */

@SpringBootApplication
//@ComponentScan(basePackages = {"com.RecyclingApp.rest.webservices.restfulwebservices.recyclingApp.core.entity;"})
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}


	// Definiert einen Bean, der CORS-Konfigurationen für die Anwendung festlegt
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Erlaubt CORS für alle Endpunkte
				.allowedMethods("*")// Erlaubt alle HTTP-Methoden (GET, POST, PUT, DELETE, etc.)
				.allowedOriginPatterns("http://localhost:3000");// Erlaubt Anfragen nur von der React-Frontend-URL
			}
		};
	}
}