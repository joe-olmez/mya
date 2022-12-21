package com.olmez.mya.springsecurity.securityutiliy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtility {

    private final String jwtSigningKey = "secret";

    /**
     * Extracts the JSON Web Token and returns the username.
     * 
     * @param jwt
     * @return username as a string
     */
    public static String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public static <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    public static Claims extractAllClaims(String wjt) {
        return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(wjt).getBody();
    }

    public static String getUsernameFromJwtToken(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        return createToken(userDetails, claimsMap);
    }

    public static String generateToken(UserDetails userDetails, Map<String, Object> claimsMap) {
        return createToken(userDetails, claimsMap);
    }

    private String createToken(UserDetails userDetails, Map<String, Object> claimsMap) {
        return Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }

    public static Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(UserDetails userDetails, String jwt) {
        String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    public static boolean hasClaim(String jwt, String claimName) {
        final Claims claims = extractAllClaims(jwt);
        return claims.get(claimName) != null;
    }

}
