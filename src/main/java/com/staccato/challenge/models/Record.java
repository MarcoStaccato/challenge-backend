package com.staccato.challenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Entity
@Table(name = "records",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
    })
public class Record {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private Long operationId;

  @NotBlank
  private Long userId;

  @NotBlank
  private Long amount;

  @NotBlank
  private Long userBalance;

  @NotBlank
  private Long operationResponse;

  @NotBlank
  private Instant date;

  @NotBlank
  private Boolean isDeleted = false;

  public Record(Long operationId, Long userId, Long amount, Long userBalance, Long operationResponse, Instant date) {
    this.operationId = operationId;
    this.userId = userId;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public Long getOperationId() {
    return operationId;
  }

  public void setOperationId(Long operationId) {
    this.operationId = operationId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getUserBalance() {
    return userBalance;
  }

  public void setUserBalance(Long userBalance) {
    this.userBalance = userBalance;
  }

  public Long getOperationResponse() {
    return operationResponse;
  }

  public void setOperationResponse(Long operationResponse) {
    this.operationResponse = operationResponse;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }
}

