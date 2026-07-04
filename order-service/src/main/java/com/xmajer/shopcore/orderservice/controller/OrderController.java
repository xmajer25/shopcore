package com.xmajer.shopcore.orderservice.controller;

import com.xmajer.shopcore.orderservice.dto.request.AddOrderItemRequest;
import com.xmajer.shopcore.orderservice.dto.response.OrderResponse;
import com.xmajer.shopcore.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Appends item to existing order or creates new order with this item.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order now contains item"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "CUSTOMER|ADMIN role required"),
            @ApiResponse(responseCode = "409", description = "Order already contains this item")
    })
    @PostMapping("/items")
    public ResponseEntity<OrderResponse> addItem(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody AddOrderItemRequest request
            ){
        UUID userId = extractUserId(jwt);

        OrderResponse response = orderService.addItem(userId, request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Changes order state from DRAFT to PAYMENT_PENDING")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order state is set to PAYMENT_PENDING"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "CUSTOMER|ADMIN role required"),
            @ApiResponse(responseCode = "409", description = "No opened order to finish")
    })
    @PatchMapping("/finish")
    public ResponseEntity<OrderResponse> finishOrder(@AuthenticationPrincipal Jwt jwt){
        UUID userId = extractUserId(jwt);
        OrderResponse response = orderService.finishOrder(userId);

        return ResponseEntity.ok(response);
    }

    private UUID extractUserId(Jwt jwt) {
        if (jwt == null || jwt.getSubject() == null) {
            throw new BadCredentialsException("Invalid JWT token");
        }

        try {
            return UUID.fromString(jwt.getSubject());
        } catch (IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid JWT subject");
        }
    }
}
