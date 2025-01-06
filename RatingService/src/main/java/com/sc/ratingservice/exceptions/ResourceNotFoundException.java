package com.sc.ratingservice.exceptions;

@SuppressWarnings("unused")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found on server!!!");
    }

    public ResourceNotFoundException(String exMsg) {
        super(exMsg);
    }
}
