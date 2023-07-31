package com.example.studentmanagement.security;



import com.example.studentmanagement.security.filters.AccessDeniedeExceptionHandler;
import com.example.studentmanagement.security.filters.ExceptionHandlerFilter;
import com.example.studentmanagement.security.filters.JWTAuthEntryHandler;
import com.example.studentmanagement.security.filters.JWTSecurityFilter;
import com.example.studentmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private static final List<String> PERMIT_URL = List.of("/user/login","/user/register");

    @Autowired
    UserService userService;

    @Autowired
    ServerConfig serverConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.antMatcher("/**").cors().and().csrf().disable()
                        .headers().frameOptions().disable().and().antMatcher("/**").authorizeRequests();

        for (String url : PERMIT_URL) {
            registry.antMatchers(url).permitAll();
        }

        registry.anyRequest().authenticated()
                .and().antMatcher("/**").exceptionHandling()
                .authenticationEntryPoint(new JWTAuthEntryHandler())
                .accessDeniedHandler(new AccessDeniedeExceptionHandler()).and()
                .addFilterBefore(new JWTSecurityFilter(authenticationManager(),userService,serverConfig), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(),JWTSecurityFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

}

