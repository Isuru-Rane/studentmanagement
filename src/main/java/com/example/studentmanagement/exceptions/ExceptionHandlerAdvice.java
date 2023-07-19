package com.example.studentmanagement.exceptions;

import com.example.studentmanagement.exceptions.http.BadRequestException;
import com.example.studentmanagement.exceptions.http.BaseException;
import com.example.studentmanagement.exceptions.http.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> handleCustomException(BaseException exception, HttpServletRequest request){
        log.error("exception occurred [BaseException] type = {} error = {}",exception.getClass().getCanonicalName(),exception.getMessage());
        exception.printStackTrace();
        return exception.getJsonResponse("requestDataProvider.getRequestHash()");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception){
        log.error("exception occurred [Exception] error = {}",exception.getMessage());
        exception.printStackTrace();
        BaseException internalServerError = new InternalErrorException(exception.getMessage());
        return internalServerError.getJsonResponse("requestDataProvider.getRequestHash()");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest){
        log.error("exception occurred [Malformed Exception] error {}",exception.getMessage());
        log.info("http response url = {}, response = {}",webRequest.getContextPath(),status.value());
        exception.printStackTrace();
        BadRequestException badRequestException = new BadRequestException("malformed request body");
        return badRequestException.getJsonResponse("requestDataProvider.getRequestHash()");
    }


}
