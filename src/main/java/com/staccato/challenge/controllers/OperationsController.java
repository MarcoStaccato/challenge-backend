package com.staccato.challenge.controllers;

import com.staccato.challenge.payload.ComputeRequest;
import com.staccato.challenge.security.jwt.JwtManager;
import com.staccato.challenge.service.ComputeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationsController {

  @Autowired
  ComputeService computeService;

  @Autowired
  JwtManager jwtManager;


  @PostMapping("/compute")
  @PreAuthorize("hasRole('USER')")
  public String compute(@Valid @RequestBody ComputeRequest computeRequest,
                        @CookieValue("challengeAuth") String jwtCookie) {

    String username = jwtManager.getUserName(jwtCookie);
    String result = computeService.runOperation(username, computeRequest);

    return result;
  }


}
