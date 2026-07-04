package com.xmajer.shopcore.orderservice.exception;

import java.util.UUID;

public class NoOpenedOrderException extends RuntimeException{
    public NoOpenedOrderException(UUID id){
        super("User with id: '" + id + "' has no opened orders");
    }
}
