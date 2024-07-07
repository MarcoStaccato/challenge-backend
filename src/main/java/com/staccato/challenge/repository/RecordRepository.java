package com.staccato.challenge.repository;

import com.staccato.challenge.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  @Query(nativeQuery = true, value = """
      select records.*
      from users inner join records on users.id = records.user_id
      where users.username = ?1 and users.is_deleted = false
      order by records.date desc limit 1
      """)
  Record findLatestRecord(String username);
}
