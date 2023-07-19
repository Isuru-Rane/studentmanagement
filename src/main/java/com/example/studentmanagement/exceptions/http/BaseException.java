package com.example.studentmanagement.exceptions.http;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class BaseException extends RuntimeException{

    private String message;
    private String type;
    private Exception rootException;
    private List<String> errors;

    public BaseException(){}

    public BaseException(String message){
        this.message = message;
    }


    public BaseException(String message, String type, Exception rootException,Object... params){
        super(message);
        this.message = formattedExceptionMessage(message,params);
        this.type = type;
        this.rootException = rootException;
    }


    public BaseException(String message, Exception rootException,Object... params){
        super(message);
        this.message = formattedExceptionMessage(message,params);
        this.rootException = rootException;
    }

    private String formattedExceptionMessage(String message, Object[] params){
        for (Object param: params){
            message = message.replaceFirst("\\{\\}}",param != null ? param.toString() : "null");
        }
        return message;

    }

    public HttpStatus getCode(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getJsonString(String hash){
        JSONObject object = new JSONObject();
        Gson gson = new Gson();
        object.put("message",this.message);
        object.put("type",this.type);
        object.put("rootException",this.rootException);
        if (this.errors != null && !this.errors.isEmpty()){
            object.put("fieldErrors", this.errors);
        }
        return gson.toJson(object);
    }

    public ResponseEntity<Object> getJsonResponse(String requestHash){
        return ResponseEntity.status(this.getCode().value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.getJsonString(requestHash));
    }

    public String getMessage() {
        if (rootException == null){
            return this.message;
        }
        return this.message + " : root exception =" + rootException.getMessage();
    }

}
