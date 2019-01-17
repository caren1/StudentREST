package com.javagda17.service.studentdemo.repository;

import com.javagda17.service.studentdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNameAndSurname(String name, String surname);

    Optional<Student> removeById(Long id);

    Optional<Student> removeByNameAndSurname(String name, String surname);

    List<Student> findAllByNameAndSurname(String name, String surname);
}
