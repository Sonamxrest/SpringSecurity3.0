package com.xrest.nchl.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Date;

public class JWTUtils {

    public static final int expiration = 21600000;
    public static final String tokenType = "Bearer";
    public static final String headerType = "Authorization";
    public static final String key = "HelloWorld";


    public static String encode(String username) {
        return JWT.create().withSubject(username).withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(Algorithm.HMAC256(key.getBytes()));
    }

    public static String decode(String token) {
        return JWT.decode(token).getSubject();
    }

    public static boolean validateToken(String token) {
        return JWT.decode(token).getExpiresAt().compareTo(new Date()) > 0;
    }

}
