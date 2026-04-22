package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        // Usar builder de Spring Framework 6 tal y como indica el skill
        return RestClient.builder().build();
        // Si la versión de Spring no soporta builder(), reemplazar por la fábrica adecuada (p. ej. RestClient.create()).
    }

}
