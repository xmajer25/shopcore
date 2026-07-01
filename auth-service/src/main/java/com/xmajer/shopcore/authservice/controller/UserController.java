package com.xmajer.shopcore.authservice.controller;

import com.xmajer.shopcore.authservice.dto.response.UserResponse;
import com.xmajer.shopcore.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "List all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All users were listed")
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> responseList = userService.getAllUsers();

        return ResponseEntity.ok(responseList);
    }
}
