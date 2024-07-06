package com.staccato.challenge.controllers;

import com.staccato.challenge.payload.ComputeRequest;
import com.staccato.challenge.payload.LoginRequest;
import com.staccato.challenge.service.ComputeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationsController {

  @Autowired
  ComputeService computeService;


  @PostMapping("/compute")
  @PreAuthorize("hasRole('USER')")
  public String compute(@Valid @RequestBody ComputeRequest computeRequest) {

    String result = computeService.compute(computeRequest);


    return result;
  }


}
