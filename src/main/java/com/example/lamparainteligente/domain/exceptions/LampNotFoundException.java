package com.example.lamparainteligente.domain.exceptions;

public class LampNotFoundException extends RuntimeException{
    public LampNotFoundException(String message){
        super(message);
    }
}
