package com.xmajer.shopcore.authservice.controller;

import com.xmajer.shopcore.authservice.dto.request.AdminCreateUserRequest;
import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminAuthController {

    private final AuthService authService;

    @Operation(summary = "Create user with selected role")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Unauthenticated"),
            @ApiResponse(responseCode = "403", description = "Admin role required"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> adminCreateUser(@Valid @RequestBody AdminCreateUserRequest request) {
        UserResponse response = authService.adminCreateUser(request);

        URI location = URI.create("/api/users/" + response.id());

        return ResponseEntity
                .created(location)
                .body(response);
    }
}