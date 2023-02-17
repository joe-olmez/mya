package com.olmez.mya.springsecurity.securityutiliy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtility {

    /**
     * To more security, you can generate a secret key from
     * https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx.
     * If you use this, you need to decode the SigninInKey(see it at the
     * buttom-*getSignInKey method)
     */
    private static final String SECRET_KEY = "secret";
    private static final String AUTH_KEY = "authorities";

    /**
     * Extracts the JSON Web Token and returns the username.
     * 
     * @param token
     * @return username as a string
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String generateToken(UserDetails userDetails, Map<String, Object> claimsMap) {
        return createToken(userDetails, claimsMap);
    }

    private String createToken(UserDetails userDetails, Map<String, Object> claimsMap) {
        return Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(userDetails.getUsername())
                .claim(AUTH_KEY, userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis())) // now
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 365)) // plus 365 days
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        return createToken(userDetails, claimsMap);
    }

    public static String getUsernameFromJwtToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(UserDetails userDetails, String token) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    // (*)
    // private Key getSignInKey(){
    // byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    // return Keys.hmacShaKeyFor(keyBytes);
    // }
    // don't forget to add this ket to Jwts.builder() in createToken(userDetails,
    // claimsMap) like .signWith(getSigninInKey(), SignatureAlgorithm.HS256))
}
