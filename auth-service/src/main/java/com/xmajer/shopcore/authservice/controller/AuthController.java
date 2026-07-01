package com.xmajer.shopcore.authservice.controller;

import com.xmajer.shopcore.authservice.dto.request.LoginUserRequest;
import com.xmajer.shopcore.authservice.dto.request.RegisterUserRequest;
import com.xmajer.shopcore.authservice.dto.response.AuthResponse;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a public user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse response = authService.registerUser(request);

        URI location = URI.create("/api/users/" + response.id());

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = "Login user and return JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginUserRequest request) {
        AuthResponse response = authService.loginUser(request);

        return ResponseEntity.ok(response);
    }
}