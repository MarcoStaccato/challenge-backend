package com.staccato.challenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
  private String operationResponse;

  @NotBlank
  private Instant date;

  @NotBlank
  private Boolean isDeleted = false;

  public Record(Long operationId, Long userId, Long amount, Long userBalance, String operationResponse, Instant date) {
    this.operationId = operationId;
    this.userId = userId;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }

}

