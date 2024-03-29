package com.word.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


public class TokenAuthenticationService {

    private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10* 2; // 20 days
    private String secret = "bubirgizlisir";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";

    public String addAuthentication(HttpServletResponse response, String username) {
        //Token GENERATE EDİLİR.
        String token;
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        response.addHeader(headerString, tokenPrefix + " " + JWT);
        return token = tokenPrefix + " "+JWT;
    }


    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token != null) {
            // tokeni ayırıp anlamdırdıgımız  yer..(PArse)
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (username != null) // Username i aldık
            {

                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
}
