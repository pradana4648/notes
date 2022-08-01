package com.pradana.notes.security;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JWTService {

    // Create JWT
    public String createToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC512("test");
            String token = JWT.create().withIssuer("auth0").sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    // Validate JWT
    public boolean isJWTValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512("test");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return false;
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
