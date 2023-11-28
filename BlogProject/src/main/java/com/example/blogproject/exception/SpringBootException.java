package com.example.blogproject.exception;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
public class SpringBootException extends RuntimeException{
    private final ErrorType errorType;

    public SpringBootException(ErrorType errorType){
        super(errorType.getMesaj());
        this.errorType=errorType;
    }
    public SpringBootException(ErrorType errorType, String mesaj){
        super(mesaj);
        this.errorType=errorType;
    }
}
