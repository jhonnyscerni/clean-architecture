package br.com.app.cleanarchitecture.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clean Architecture Application API")
                        .version("1.0")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."))
                .addServersItem(new Server().url("http://localhost:8080/api").description("Development server"));
    }
}