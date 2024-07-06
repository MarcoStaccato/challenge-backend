package com.staccato.challenge.repository;

import com.staccato.challenge.models.Record;
import com.staccato.challenge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

}
