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
@Table(name = "operations",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
    })
public class Operation {
  @Id
  private Long id;

  @NotBlank
  private String type;

  @NotBlank
  private Long cost;

  @NotBlank
  private Boolean isDeleted = false;

  public Operation(String type, Long cost) {
    this.type = type;
    this.cost = cost;
  }

}

