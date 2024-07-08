package com.staccato.challenge.repository;

import com.staccato.challenge.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  @Query(nativeQuery = true, value = """
      select *
      from records where user_id= ?1 and is_deleted = false
      order by date desc limit 1
      """)
  Record findLatestRecord(Integer userId);


  @Query(nativeQuery = true, value= "select * from records where user_id = ?1 and is_deleted = false")
  Page<Record> findRecordsByUserId(Integer userId, Pageable pageable);

}
