package com.banking.auth_service.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public  CustomerAlreadyExistsException(String msg){
        super(msg);
    }

}
