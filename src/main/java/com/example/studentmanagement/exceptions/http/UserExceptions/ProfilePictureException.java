package com.example.studentmanagement.exceptions.http.UserExceptions;


import com.example.studentmanagement.exceptions.http.BaseException;
import com.example.studentmanagement.exceptions.user.UserExType;
import org.springframework.http.HttpStatus;

public class ProfilePictureException extends BaseException {
    public ProfilePictureException(String message, UserExType exType){
        super(message);
        setType(exType.name());
        setStatusCode(415);
    }
    @Override
    public HttpStatus getCode(){
        return HttpStatus.BAD_REQUEST;
    }
}
