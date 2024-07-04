package com.fakepi.ShoeStore.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


  private static final String SECRET_KEY = "f2c9487006b4c29195b2ea7f2a487e54bc6ca20c4f66988626dd07c89cdfd79e";


  public String getToken(UserDetails user) {
    return getToken(new HashMap<>(), user);
  }

  private String getToken(Map<String, Object> extraClaims, UserDetails user) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getKey())
        .compact();
  }

  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  private Claims getAllClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public <T> T getClaim (String token, Function<Claims,T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration (String token) {
    return getClaim(token, Claims::getExpiration);
  }

  private Boolean isTokenExpired (String token) {
    return getExpiration(token).before(new Date());
  }
}
