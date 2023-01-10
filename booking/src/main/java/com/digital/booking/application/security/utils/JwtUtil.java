package com.digital.booking.application.security.utils;

import com.digital.booking.application.security.models.UserSecurityDetails;
import com.digital.booking.core.port.output.SecurityJwtRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil implements SecurityJwtRepository {

    private final Integer EXPIRATION_TIME = 3600000;

    private final String SECRET_KEY = "12345678abcdefgh";

    @Override
    public boolean isExpiredToken(String token) {
        return extractTokenExpirationDate(token).before(new Date());
    }

    @Override
    public String getUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractTokenExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public String buildJwtToken(UserSecurityDetails user) {
        return generateToken(buildClaims(user), user.getUserName());
    }

    private <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(extractClaims(token));
    }

    private Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Map<String, Object> buildClaims(UserSecurityDetails user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", user.isAdmin());
        claims.put("sub", user.getUserName());
        claims.put("name", user.getUserName());
        return claims;
    }

    private String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public Boolean isValidToken(String token, UserSecurityDetails securityDetails) {
        String userEmail =  getUserEmail(token);
        return (userEmail.equalsIgnoreCase(securityDetails.getUserName()) && !isExpiredToken(token) );
    }
}
