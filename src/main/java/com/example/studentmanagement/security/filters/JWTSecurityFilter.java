package com.example.studentmanagement.security.filters;



import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.studentmanagement.exceptions.http.UnauthorizedException;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.security.ServerConfig;
import com.example.studentmanagement.security.Session;
import com.example.studentmanagement.security.jwt.JWT;
import com.example.studentmanagement.service.UserService;
import com.example.studentmanagement.security.jwt.JWTContent;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.syntax.TokenException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JWTSecurityFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    private final ServerConfig serverConfig;

    public JWTSecurityFilter(AuthenticationManager authenticationManager,UserService userService,ServerConfig serverConfig) {
        super(authenticationManager);
        this.userService = userService;
        this.serverConfig = serverConfig;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null){
            token = request.getParameter("_authorization");
        }
        if (token == null){
            log.debug("token not found");
            chain.doFilter(request,response);
            return;
        }

        try {
            JWTContent jwtContent = JWT.decode(token,serverConfig.getSecretKey());
            Optional<User> user = this.userService.findUserById(Integer.parseInt(jwtContent.getSubject()));
            if (user.isEmpty()){
                throw new UnauthorizedException("User Not Found");
            }
            Session.setUser(user.get());
        }catch (TokenExpiredException exception){
            request.setAttribute("tok-expired",true);
        }catch (Exception e){
            log.error("token decode failed");
        }
        chain.doFilter(request,response);
    }
}
