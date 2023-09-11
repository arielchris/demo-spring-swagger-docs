package com.example.demospringswaggerdocs.controller;

import com.example.demospringswaggerdocs.domain.Users;
import com.example.demospringswaggerdocs.domain.request.UserRequest;
import com.example.demospringswaggerdocs.domain.response.ApiResponse;
import com.example.demospringswaggerdocs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Users API", description = "Users API")
public class UsersController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get user by id", description = "return user by id", parameters = {
                @Parameter(name = "id", in = ParameterIn.PATH, required = true, description = "user id")
             })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404:101",
                    description = "Not Found - user id was not found")
    })
    @GetMapping("/view/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){

        Users users = userService.getUserById(id);

        if(ObjectUtils.isEmpty(users)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.build(101, "user id not found"));
        }

        return ResponseEntity.ok(ApiResponse.build(0, "success")
                .putDataMap("users", users));
    }

    @Operation(summary = "Get all users", description = "return all users")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "success"
                    /*,content = {
                        @Content(mediaType = "application/json", schema = @Schema(description = "code", example = "0")),
                    }*/
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404:100",
                    description = "Not Found - User empty")
    })
    @GetMapping("/view")
    public ResponseEntity<?> getAllUser(){
        List<Users> users = userService.getAllUser();
        if(ObjectUtils.isEmpty(users)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.build(100, "users empty"));
        }
        return ResponseEntity.ok(ApiResponse.build(0, "success")
                .putDataMap("users", users));
    }

    @Operation(summary = "Create new user", description = "Create new user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400:102",
                    description = "Bad Request - Username already taken"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400:103",
                    description = "Bad Request - Email already taken")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){

        if(userService.isUsernameAlreadyTaken(userRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.build(102, "username already taken"));
        } else if (userService.isEmailAlreadyTaken(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.build(103, "email already taken"));
        }

        Users users = userService.createUser(userRequest);

        return ResponseEntity.ok(ApiResponse.build(0, "success")
                .putDataMap("user", users));
    }

    @Operation(summary = "Delete user by id", description = "return response delete status", parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, required = true, description = "user id")
    })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404:101",
                    description = "Not Found - user id was not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        if(ObjectUtils.isEmpty(userService.getUserById(id))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.build(101, "User id not found"));
        }

        userService.deleteUser(id);

        return ResponseEntity.ok(ApiResponse.build(0, "success"));
    }

    @Operation(summary = "Update user", description = "Update user response new user info", parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, required = true, description = "user id")
    })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400:102",
                    description = "Bad Request - Username already taken"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400:103",
                    description = "Bad Request - Email already taken")
    })
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){

        if(userService.isUsernameAlreadyTaken(userRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.build(102, "username already taken"));
        } else if (userService.isEmailAlreadyTaken(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.build(103, "email already taken"));
        }

        Users usersResult = userService.updateUser(id, userRequest);

        return ResponseEntity.ok(ApiResponse.build(0, "success")
                .putDataMap("user", usersResult));
    }
}
