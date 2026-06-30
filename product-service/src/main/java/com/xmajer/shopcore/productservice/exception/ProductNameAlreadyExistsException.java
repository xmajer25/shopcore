package com.xmajer.shopcore.productservice.exception;

public class ProductNameAlreadyExistsException extends RuntimeException{
    public ProductNameAlreadyExistsException(String name){
        super("Product with name: '" + name + "' already exists");
    }
}
