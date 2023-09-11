package com.example.demospringswaggerdocs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demospringswaggerdocs.repository")
@OpenAPIDefinition(info = @Info(title = "Example API"
        , version = "0.0.1"
        , description = "Example Api using spring swagger docs"))
public class DemoSpringSwaggerDocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringSwaggerDocsApplication.class, args);
    }

}
