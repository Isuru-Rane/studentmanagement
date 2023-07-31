package com.example.studentmanagement.security.filters;

import com.example.studentmanagement.exceptions.http.BaseException;
import com.example.studentmanagement.exceptions.http.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request,response);
        }catch (BaseException baseException){
            baseException.printStackTrace();
            log.error("exception occurred. error {}", baseException.getMessage());
        }catch (Exception exception){
            exception.printStackTrace();
            log.error("exception occurred. error {}", exception.getMessage());
            BaseException baseException = new InternalErrorException(exception.getMessage());
            this.setErrorResponse(response,baseException);
        }
    }

    private void setErrorResponse(HttpServletResponse response,BaseException baseException){
        response.setStatus(baseException.getCode().value());
        response.setContentType("application/json");
        try {
            String json = baseException.getJsonString(null);
            response.getWriter().write(json);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getServletPath();
        return !path.startsWith("/api/");
    }
}
