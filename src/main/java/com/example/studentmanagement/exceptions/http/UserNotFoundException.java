package com.example.studentmanagement.exceptions.http;

import com.example.studentmanagement.exceptions.user.UserExType;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException(String message){
        super(message);
        setType("USER_NOT_FOUND");

    }

    public UserNotFoundException(String message, UserExType exType){
        super(message);
        setType(exType.name());
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;

    }




}
