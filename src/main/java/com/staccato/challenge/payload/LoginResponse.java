package com.staccato.challenge.payload;

import lombok.Data;

@Data
public class LoginResponse {

  private String username;

  public LoginResponse(String username) {
    this.username = username;
  }

}
