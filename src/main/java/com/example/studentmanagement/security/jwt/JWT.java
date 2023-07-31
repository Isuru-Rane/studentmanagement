package com.example.studentmanagement.security.jwt;



import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWT {

    public static String encode(JWTContent content, String secretKey) {
        JWTCreator.Builder jwtBuilder  = com.auth0.jwt.JWT.create();
        if (content.getPayload() != null){
            for (Map.Entry<String,String> entry: content.getPayload().entrySet()) {
                jwtBuilder = jwtBuilder.withClaim(entry.getKey(),entry.getValue());
            }
        }
        String token = jwtBuilder.withSubject(content.getSubject())
                .withExpiresAt(DateUtils.addMilliseconds(new Date(), (int) content.getExpiredIn()))
                .sign(Algorithm.HMAC256(secretKey));

        return token;
    }

    public static JWTContent decode(String token, String secretKey){
        try {
            JWTContent content = JWTContent.builder().build();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            content.setSubject(decodedJWT.getSubject());
            Map<String,String> payload = new HashMap<>();
            for (Map.Entry<String, Claim> entry: decodedJWT.getClaims().entrySet()) {
                payload.put(entry.getKey(),entry.getValue().toString());
            }
            content.setPayload(payload);
            return content;
        }catch (JWTVerificationException exception){
            log.error("token decode failed. error = {}", exception.getMessage());
            throw exception;
        }
    }
}
