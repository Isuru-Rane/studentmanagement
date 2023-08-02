package com.example.studentmanagement.exceptions.http;



import com.example.studentmanagement.security.ServerConfig;
import com.example.studentmanagement.security.Session;
import com.example.studentmanagement.security.jwt.JWT;
import com.example.studentmanagement.security.jwt.JWTContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class JWTAppendService implements ResponseBodyAdvice<Object> {

    @Autowired
    ServerConfig serverConfig;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (Session.getUser() == null){
            return body;
        }
        JWTContent.JWTContentBuilder content = JWTContent.builder().subject(String.valueOf(Session.getUser().getId()));
        content.expiredIn(DateUtils.MILLIS_PER_SECOND * serverConfig.getTokenExpireTime());
        String token = JWT.encode(content.build(), serverConfig.getSecretKey());
        response.getHeaders().add("Authorization",token);
        return body;
    }
}