package com.example.demospringswaggerdocs.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return null;
    }
}
