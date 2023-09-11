package com.example.demospringswaggerdocs.controller;

import com.example.demospringswaggerdocs.domain.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Root API", description = "document root path")
public class AppController {
    @GetMapping("/")
    public ResponseEntity<?> root(){
        return ResponseEntity.ok(ApiResponse.build(0, "success"));
    }
}
