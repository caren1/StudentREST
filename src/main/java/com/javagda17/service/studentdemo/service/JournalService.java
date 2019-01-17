package com.javagda17.service.studentdemo.service;

import com.javagda17.service.studentdemo.exceptions.JournalNotFound;
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

    public boolean removeJournalById(Long id) {
        Optional<Journal> journalOptional = journalRepository.removeById(id);
        return journalOptional.isPresent();
    }

    public boolean updateJournal(Journal journal) {
        Optional<Journal> dziennikOptional = getJournal(journal.getId());
        if (dziennikOptional.isPresent()) {
            Journal journalFromDB = dziennikOptional.get();

            journalFromDB.setClassName(journal.getClassName());
            journalFromDB.setSchoolName(journal.getSchoolName());
            journalFromDB.setTutorName(journal.getTutorName());
            journalFromDB.setYear(journal.getYear());
            journalFromDB.setStudentList(journal.getStudentList());

            journalRepository.save(journalFromDB);
            return true;
        }
        return false;
    }

    public Optional<Journal> getJournal(Long id) {
        return journalRepository.findById(id);
    }

    public Optional<Journal> removeStudentFromJournal(Long id, Long journalId) {
        Optional<Student> studentOptional = studentService.getStudent(id);
        if (!studentOptional.isPresent()) {
            throw new StudentNotFound();
        }
        Optional<Journal> dziennikOptional = journalRepository.findById(journalId);
        if (!dziennikOptional.isPresent()) {
            throw new JournalNotFound();
        }
        Journal journal = dziennikOptional.get();
        Student student = studentOptional.get();

        journal.getStudentList().remove(student);
        journal = journalRepository.save(journal);

        return Optional.of(journal);
    }
}
