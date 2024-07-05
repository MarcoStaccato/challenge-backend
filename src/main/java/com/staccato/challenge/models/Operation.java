package com.staccato.challenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getCost() {
    return cost;
  }

  public void setCost(Long cost) {
    this.cost = cost;
  }

  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }
}

