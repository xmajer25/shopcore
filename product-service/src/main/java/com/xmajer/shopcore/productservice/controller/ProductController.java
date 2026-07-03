package com.xmajer.shopcore.productservice.controller;

import com.xmajer.shopcore.productservice.dto.request.CreateProductRequest;
import com.xmajer.shopcore.productservice.dto.response.ProductResponse;
import com.xmajer.shopcore.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Creates new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Admin role required"),
            @ApiResponse(responseCode = "409", description = "Product name already exists")
    })
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request){
        ProductResponse response = productService.create(request);

        URI location = URI.create("/api/products/" + response.id());

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = "Fetches all products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products fetched")
    })
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProductResponse> response = productService.getAll();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Fetches product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product was fetched"),
            @ApiResponse(responseCode = "404", description = "Product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id){
        ProductResponse response = productService.getById(id);

        return ResponseEntity.ok(response);
    }
}
