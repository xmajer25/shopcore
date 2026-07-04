package com.xmajer.shopcore.orderservice.client;

import com.xmajer.shopcore.orderservice.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class ProductClient {
    private final RestClient restClient;

    public ProductClient(
            RestClient.Builder restClientBuilder,
            @Value("${app.services.product-service.url}") String productServiceUrl
    ) {
        this.restClient = restClientBuilder
                .baseUrl(productServiceUrl)
                .build();
    }

    public ProductResponse getProductById(UUID productId) {
        return restClient
                .get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);
    }
}
