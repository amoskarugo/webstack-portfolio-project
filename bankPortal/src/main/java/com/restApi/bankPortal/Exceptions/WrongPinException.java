package com.restApi.bankPortal.Exceptions;

public class WrongPinException extends RuntimeException{

    public WrongPinException(String message){
        super(message);
    }
}
