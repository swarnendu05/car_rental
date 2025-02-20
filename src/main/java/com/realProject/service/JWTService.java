package com.realProject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {


    @Value("${jwt.key}")
private String algorithmkey;

    @Value("${jwt.issuer}")
private String issuer;

    @Value("${jwt.expiry}")
private int expiry;

    private Algorithm algorithm;


    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(String username){
         return  JWT.create().withClaim("username",username)
        .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
                 .withIssuer(issuer)
                 .sign(algorithm);
    }

    public String getName(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("username").asString();
    }
}
