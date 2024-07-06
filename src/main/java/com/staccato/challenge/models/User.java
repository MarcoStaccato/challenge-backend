package com.staccato.challenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 100)
  @Email
  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  private Boolean isDeleted = false;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

}

