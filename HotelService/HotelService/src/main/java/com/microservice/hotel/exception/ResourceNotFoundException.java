package com.microservice.hotel.exception;

public class ResourceNotFoundException extends RuntimeException {

    public  ResourceNotFoundException() {
        super("cannot found user");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
