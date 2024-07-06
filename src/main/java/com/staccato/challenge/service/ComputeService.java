package com.staccato.challenge.service;

import com.staccato.challenge.core.Operations;
import com.staccato.challenge.core.OperationsEnum;
import com.staccato.challenge.models.User;
import com.staccato.challenge.payload.ComputeRequest;
import com.staccato.challenge.repository.RecordRepository;
import com.staccato.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputeService {

  private final RecordRepository recordRepository;
  private final UserRepository userRepository;


  public boolean isBalanceAvailable(String username) {
    Optional<User> user =  userRepository.findByUsername(username);

    if(user.isEmpty()) {
      throw new IllegalArgumentException("User does not exists");
    }

    recordRepository.findById(user.get().getId());

    return false;
  }


  public String compute(ComputeRequest request) {

    OperationsEnum operation = OperationsEnum.get(request.getOperation());

    Double operator = request.getOperator();
    Double numerator = request.getNumerator();

    String result = "";

    switch (operation) {
      case ADDITION -> result = Operations.addition(operator, numerator).toString();
      case SUBSTRACTION -> result = Operations.substraction(operator, numerator).toString();
      case MULTIPLICATION -> result = Operations.multiplication(operator, numerator).toString();
      case DIVISION -> result = Operations.division(operator, numerator).toString();
      case SQUARE_ROOT -> result = Operations.sqrt(operator).toString();
      case RANDOM_STRING -> result = Operations.random();
    }

    return result;
  }

}
