package com.sw.restaurant.exception;

public class CustomerException extends Exception{

    @Override
    public String getMessage() {
        return "Customer Exception" + super.getMessage();
    }
}
