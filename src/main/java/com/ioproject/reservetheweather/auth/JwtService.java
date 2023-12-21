package com.ioproject.reservetheweather.auth;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Serwis do zarządzania tokenami JWT.
 */
@Service
public class JwtService {

    private String SECRET_KEY = "6a349f0b7be0462b8172f834cdeeb9059d44ec57424bc6b9091805c62bcb7969895c9c49f82b147dd3f7b36c7c42f97f5684cc2604bf25a6cd6c35eaff8f1c7e215ff0cf9671f4b0edafaef8452bec65";

    /**
     * Generuje token JWT dla podanych danych użytkownika.
     * @param userDetails Klasa z danymi użytkownika.
     * @return Wygenerowany token JWT.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1080 * 68 * 2400))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * Generuje odświeżony token JWT.
     * @param extraClaims
     * @param userDetails
     * @return Wygenerowany odświeżony token JWT.
     */
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1080 * 68 * 24 *7))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private<T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private Key getSigninKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }


    /**
     * Sprawdza, czy token JWT jest ważny.
     * @param token Token JWT.
     * @param userDetails Klasa z danymi użytkownika.
     * @return true jeśli token jest ważny, w przeciwnym razie false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String name = extractUserName(token);
        return (name.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * Sprawdza, czy token JWT wygasł.
     * @param token Token JWT.
     * @return true jeśli token wygasł, w przeciwnym razie false.
     */
    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }


}