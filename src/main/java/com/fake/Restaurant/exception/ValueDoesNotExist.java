package com.fake.Restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "value does not exist")
public class ValueDoesNotExist extends Exception{
    public ValueDoesNotExist(String message){
        super(message);
    }
}
