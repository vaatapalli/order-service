package com.orderservice.exceptions;

public class ItemNotFoundExcpetion extends RuntimeException{
    public ItemNotFoundExcpetion(String message) {
        super(message);
    }
}
