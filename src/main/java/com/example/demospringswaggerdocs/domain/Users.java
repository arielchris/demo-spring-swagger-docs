package com.example.demospringswaggerdocs.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Users {

    @Schema(name = "id", description = "User ID", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Schema(name = "username", description = "username", example = "admin")
    private String username;
    @Schema(name = "password", description = "password", example = "password")
    private String password;
    @Schema(name = "firstName", description = "fistName", example = "Super")
    private String firstName;
    @Schema(name = "lastName", description = "lastName", example = "Admin")
    private String lastName;
    @Schema(name = "email", description = "email", example = "admin@example.com")
    private String email;

}
