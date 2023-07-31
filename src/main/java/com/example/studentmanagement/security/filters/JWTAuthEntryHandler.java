package com.example.studentmanagement.security.filters;

import com.example.studentmanagement.exceptions.http.UnauthorizedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthEntryHandler  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final boolean isExpired = request.getAttribute("tok-expired") != null;
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (isExpired){
            response.getOutputStream().println(new UnauthorizedException("TOKEN_EXPIRED","token expired").getJsonString(null));
        } else {
            response.getOutputStream().println(new UnauthorizedException("AUTH_TOKEN_INVALID","auth token invalid").getJsonString(null));
        }
    }
}
