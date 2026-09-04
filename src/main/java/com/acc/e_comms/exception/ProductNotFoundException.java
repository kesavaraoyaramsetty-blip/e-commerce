//creating custom exception
package com.acc.e_comms.exception;
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
