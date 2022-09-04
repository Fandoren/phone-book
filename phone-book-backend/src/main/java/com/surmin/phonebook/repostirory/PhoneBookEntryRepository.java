package com.surmin.phonebook.repostirory;

import com.surmin.phonebook.model.PhoneBookEntry;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhoneBookEntryRepository extends JpaRepository<PhoneBookEntry, Long> {

    @Query(value = "UPDATE phone_book SET actualized = :dateTime", nativeQuery = true)
    @Transactional
    @Modifying
    void actualizeAllRecords(LocalDateTime dateTime);
}
