package com.orderservice.exceptions;

public class OrderNotFoundExcpetion extends RuntimeException {

    public OrderNotFoundExcpetion(String message) {
        super(message);
    }
}
