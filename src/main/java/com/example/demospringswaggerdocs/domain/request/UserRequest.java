package com.example.demospringswaggerdocs.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Schema(name = "username", example = "admin")
    private String username;
    @Schema(name = "password", example = "password")
    private String password;
    @Schema(name = "fistName", example = "Super")
    private String firstName;
    @Schema(name = "lastName", example = "Administrator")
    private String lastName;
    @Schema(name = "email", example = "admin@example.com")
    private String email;
}
