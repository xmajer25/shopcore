package com.xmajer.shopcore.productservice.config;

import com.xmajer.shopcore.productservice.data.model.Product;
import com.xmajer.shopcore.productservice.data.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDataSeeder implements ApplicationRunner {

    private final ProductRepository productRepository;

    public ProductDataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        seedProduct("Mechanical Keyboard", 7999L);
        seedProduct("Wireless Mouse", 2999L);
        seedProduct("USB-C Docking Station", 11999L);
        seedProduct("Gaming Headset", 5999L);
        seedProduct("27-inch Monitor", 18999L);
    }

    private void seedProduct(String name, Long price) {
        if (productRepository.existsByName(name)) {
            return;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        productRepository.save(product);
    }
}