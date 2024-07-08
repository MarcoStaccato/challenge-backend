package com.staccato.challenge.service;

import com.staccato.challenge.core.Operations;
import com.staccato.challenge.core.OperationsEnum;
import com.staccato.challenge.models.Operation;
import com.staccato.challenge.models.Record;
import com.staccato.challenge.payload.ComputeRequest;
import com.staccato.challenge.repository.OperationRepository;
import com.staccato.challenge.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ComputeService {

  private final RecordRepository recordRepository;
  private final OperationRepository operationRepository;


  private final String NOT_ENOUGH_BALANCE = "YOU DON'T HAVE ENOUGH BALANCE TO RUN THIS OPERATION";

  public String runOperation(Integer username, ComputeRequest request) {

    //check for balance
    Record latestRecord = recordRepository.findLatestRecord(username);
    Optional<Operation> optionalOperation = operationRepository.findByType(request.getOperation());

    if(optionalOperation.isEmpty()
        || latestRecord == null
        || (latestRecord.getUserBalance() - optionalOperation.get().getCost()) < 0) {
      return NOT_ENOUGH_BALANCE;
    }

    String result = compute(request);

    Operation currentOp = optionalOperation.get();
    Long newBalance = latestRecord.getUserBalance() - currentOp.getCost();

    Record record = new Record(currentOp.getId(), latestRecord.getUserId(), currentOp.getCost(), newBalance, result, Instant.now());

    recordRepository.save(record);

    return result;
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


  public List<Record> getRecords(Integer userId, String field, int pageNumber, int pageSize, String sort) {
    Pageable sortedPage;

    if("desc".equalsIgnoreCase(sort)){
      sortedPage = PageRequest.of(pageNumber, pageSize, Sort.by(field).descending());
    } else {
      sortedPage = PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending());
    }

    Page<Record> recordPage = recordRepository.findRecordsByUserId(userId, sortedPage);
    return recordPage.get().collect(Collectors.toList());
  }
}
