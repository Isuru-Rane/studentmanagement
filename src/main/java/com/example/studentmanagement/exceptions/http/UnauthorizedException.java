package com.example.studentmanagement.exceptions.http;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String type,String message){
        super(message,type);
    }

    public UnauthorizedException(String message){
        super(message);
    }

}

