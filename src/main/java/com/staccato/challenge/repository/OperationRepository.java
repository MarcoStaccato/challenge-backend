package com.staccato.challenge.repository;

import com.staccato.challenge.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

  Optional<Operation> findByType(String type);
}
