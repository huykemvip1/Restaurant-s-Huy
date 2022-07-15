package com.fake.Restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Already Exist")
public class AlreadyExist extends RuntimeException{
    public AlreadyExist(String message){
        super(message);
    }
}
