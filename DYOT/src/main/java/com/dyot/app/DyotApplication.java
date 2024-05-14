package com.dyot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DyotApplication {

	public static void main(String[] args) {
		SpringApplication.run(DyotApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*") // Permite todas las solicitudes desde cualquier origen
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
						.allowedHeaders("*"); // Encabezados permitidos
			}
		};
	}
}
