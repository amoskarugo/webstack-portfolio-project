package com.restApi.bankPortal.Exceptions;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
