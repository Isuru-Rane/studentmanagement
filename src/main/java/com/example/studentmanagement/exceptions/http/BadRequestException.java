package com.example.studentmanagement.exceptions.http;

import com.example.studentmanagement.exceptions.user.UserExType;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException{

    public BadRequestException() {
        setType("BAD_REQUEST");
    }

    public BadRequestException(String message) {
        super(message);
        setType("BAD_REQUEST");
    }

    public BadRequestException(String message, UserExType exType){
        super(message);
        setType(exType.name());
    }

    public BadRequestException(Exception rootException,String message,Object... parms) {
        super(message,rootException,parms);
        setType("BAD_REQUEST");
    }

    public  BadRequestException(Exception rootException,String message, String type,Object... parms){
        super(message,type,rootException,parms);
    }

    @Override
    public HttpStatus getCode(){
        return HttpStatus.BAD_REQUEST;
    }

}
