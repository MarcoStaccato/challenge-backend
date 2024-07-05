package com.staccato.challenge.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;

@Component
@Log4j2
public class JwtManager {

  @Value("${challenge.app.jwtSecret}")
  private String jwtSecret;

  @Value("${challenge.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${challenge.app.jwtCookieName}")
  private String jwtCookie;

  public ResponseCookie generateJwtCookie(String username) {
    String jwt = generateToken(username);
    ResponseCookie cookie = ResponseCookie
        .from(jwtCookie, jwt)
        .path("/api")
        .maxAge(24 * 60 * 60)
        .httpOnly(true)
        .build();
    return cookie;
  }

  public ResponseCookie generateEmptyCookie() {
    return ResponseCookie.from(jwtCookie, null).path("/api").build();
  }

  public String generateToken(String username) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(key())
        .compact();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (Exception e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    }
    return false;
  }

  public String getJwt(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }

  public String getUserName(String token) {
    return Jwts.parser().setSigningKey(key()).build()
        .parseClaimsJws(token).getBody().getSubject();
  }
}
