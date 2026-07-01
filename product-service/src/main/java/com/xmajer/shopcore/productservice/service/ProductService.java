package com.xmajer.shopcore.productservice.service;

import com.xmajer.shopcore.productservice.data.model.Product;
import com.xmajer.shopcore.productservice.data.repository.ProductRepository;
import com.xmajer.shopcore.productservice.dto.request.CreateProductRequest;
import com.xmajer.shopcore.productservice.dto.response.ProductResponse;
import com.xmajer.shopcore.productservice.exception.ProductNameAlreadyExistsException;
import com.xmajer.shopcore.productservice.exception.ProductNotFoundException;
import com.xmajer.shopcore.productservice.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse create(CreateProductRequest request){
        if(productRepository.existsByName(request.name())){
            throw new ProductNameAlreadyExistsException(request.name());
        }

        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    public List<ProductResponse> getAll(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse getById(UUID id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.toResponse(product);
    }
}
