package com.staccato.challenge.controllers;

import com.staccato.challenge.models.Record;
import com.staccato.challenge.payload.ComputeRequest;
import com.staccato.challenge.security.jwt.JwtManager;
import com.staccato.challenge.service.ComputeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    Integer userId = jwtManager.getUserId(jwtCookie);
    String result = computeService.runOperation(userId, computeRequest);

    return result;
  }

  @GetMapping("/records")
  @PreAuthorize("hasRole('USER')")
  public List<Record> getRecords(@CookieValue("challengeAuth") String jwtCookie) {

    Integer userId = jwtManager.getUserId(jwtCookie);
    List<Record> records = computeService.getRecords(userId, "date", 0, 5, "desc");

    return records;
  }

}
