package com.example.studentmanagement.exceptions.http;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends BaseException{
    public InternalErrorException(String message) {
        super(message);
        setType("INTERNAL_ERROR");
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
