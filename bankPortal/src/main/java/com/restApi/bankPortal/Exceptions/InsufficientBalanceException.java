package com.restApi.bankPortal.Exceptions;

import java.util.HashMap;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
