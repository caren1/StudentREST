package com.javagda17.service.studentdemo.repository;

import com.javagda17.service.studentdemo.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByClassName(String className);
}
