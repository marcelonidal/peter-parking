package com.grupo7.peter_parking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Peter Parking API",
                version = "1.0.0",
                description = "API para Gestao de Parquimetros, Carros e Pessoas"
        ),
        servers = @Server(url = "http://localhost:8080/parquimetro-api", description = "Ambiente Local")
)
public class OpenApiConfig {
}
