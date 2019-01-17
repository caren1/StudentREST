package com.javagda17.service.studentdemo.service;

import com.javagda17.service.studentdemo.exceptions.StudentNotFound;
import com.javagda17.service.studentdemo.model.Journal;
import com.javagda17.service.studentdemo.model.Student;
import com.javagda17.service.studentdemo.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private StudentService studentService;

    public Optional<Journal> create(Journal journal) {
        Optional<Journal> optionalJournal = journalRepository.findByClassName(journal.getClassName());
        if (!optionalJournal.isPresent()) {
            journal = journalRepository.save(journal);

            // dodawanie się powiodło.
            return Optional.of(journal);
        }
        return Optional.empty();
    }

    public Optional<Journal> create(String className, String schoolName, Integer year, String tutor) {
        return create(new Journal(null, schoolName, className, year, tutor, null));
    }

    public Optional<Journal> getById(Long id) {
        return journalRepository.findById(id);
    }

    public Optional<Journal> addStudentToJournal(Long id, Long journalId) {
        Optional<Student> studentOptional = studentService.getStudent(id);
        if (!studentOptional.isPresent()) {
            throw new StudentNotFound();
        }
        Optional<Journal> journalOptional = journalRepository.findById(journalId);
        if (!journalOptional.isPresent()) {
            return Optional.empty();
        }
        Journal journal = journalOptional.get();
        Student student = studentOptional.get();

        journal.getStudentList().add(student);
        journal = journalRepository.save(journal);

        return Optional.of(journal);
    }
}
