package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";

    // me permet d'acceder au valeur des attributs existe dans le fichier application.yml
    @Value("${auth.expiration}")
    private Long TOKEN_VALIDITY = 604800L; //en second

    @Value("${auth.secret}")
    private String TOKEN_SECRET;
    public String generateToken(UserDetails userDetails){

        //Claims
        //Expiration
        //Sign
        //Compact

        Map<String, Object> claims =new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateTokenDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    public Date generateTokenDate(){
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public String getUserNameFromToken(String token){
          try {
              Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                      .parseClaimsJws(token)
                      .getBody();
              return claims.getSubject();
          }catch (Exception e){
              return null;
          }
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody().getExpiration();

        return expiration.before(new Date());
    }
}
