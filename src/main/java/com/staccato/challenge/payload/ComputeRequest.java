package com.staccato.challenge.payload;

import com.staccato.challenge.core.OperationsEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComputeRequest {

  private Double operator;

  private Double numerator;

  @NotBlank
  private String operation;

}
