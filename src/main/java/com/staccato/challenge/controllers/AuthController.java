package com.staccato.challenge.controllers;

import com.staccato.challenge.models.Record;
import com.staccato.challenge.models.User;
import com.staccato.challenge.payload.LoginRequest;
import com.staccato.challenge.payload.LoginResponse;
import com.staccato.challenge.payload.SimpleResponse;
import com.staccato.challenge.repository.RecordRepository;
import com.staccato.challenge.repository.UserRepository;
import com.staccato.challenge.security.jwt.JwtManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtManager jwtManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RecordRepository recordRepository;

  @Value("${challenge.app.jwtCookieName}")
  private String jwtTokenName;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
    ResponseCookie jwtCookie = jwtManager.generateJwtCookie(loginRequest.getUsername(), user.get().getId());

    return ResponseEntity.ok()
        .header(jwtTokenName, jwtCookie.getValue())
        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString()) //for api testing
        .body(new LoginResponse(loginRequest.getUsername()));
  }

//  Used to initialize local users
  @PostMapping("/signup")
  @Profile("local")
  public ResponseEntity<?> createUser(@Valid @RequestBody LoginRequest loginRequest) {

    User user = new User();
    user.setUsername(loginRequest.getUsername());
    user.setPassword(encoder.encode(loginRequest.getPassword()));
    User savedUser = userRepository.save(user);

    Record rec = new Record(1L, savedUser.getId(), 0L, 20L, "SUCCESS", Instant.now());
    recordRepository.save(rec);

    return ResponseEntity.ok().body(new LoginResponse(loginRequest.getUsername()));
  }


  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtManager.generateEmptyCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new SimpleResponse("You've been signed out!"));
  }



}
