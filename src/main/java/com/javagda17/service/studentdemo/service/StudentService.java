package com.javagda17.service.studentdemo.service;

import com.javagda17.service.studentdemo.model.Student;
import com.javagda17.service.studentdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    // todo: zad 5. - zmodyfikuj metodę addstudent tak aby nie pozwalała na zapis drugiego studenta o tym samym
    //  imieniu i (!) nazwisku.

    // todo: zad 1a. - Metoda addStudent(Student):boolean która zapisuje studenta w bazie
    public boolean addStudent(Student student) {
        if (!getStudent(student.getName(), student.getSurname()).isPresent()) {
            // jeśli nie istnieje, to mogę zapisać do bazy
            student = studentRepository.save(student);
            return true;
        }
        return false;
    }

    // todo: zad 1b. - Metoda addStudent(String, String):boolean która zapisuje studenta w bazie
    public boolean addStudent(String name, String surname) {
        return addStudent(new Student(null, name, surname));
    }

    // todo: zad 2a. - Metoda getStudent(Long):Optional<Student> która pobiera studenta o podanym id z bazy
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    // todo: zad 2b. - Metoda getStudent(String,String):Optional<Student> która pobiera studenta o
    //  podanym imieniu i nazwisku z bazy
    public Optional<Student> getStudent(String name, String surname) {
        return studentRepository.findByNameAndSurname(name, surname);
    }

    // todo: zad 3. - Metoda removeStudent(Long):boolean która usuwa studenta o podanym id z bazy
    public boolean removeStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.removeById(id);
        return studentOptional.isPresent();
    }

    // todo: zad 4. - Metoda updateStudent(Student):boolean która aktualizuje studenta o podanym id w bazie
    public boolean updateStudent(Student student) {
        Optional<Student> studentOptional = getStudent(student.getId());
        if (studentOptional.isPresent()) {
            Student studentFromDB = studentOptional.get();

            studentFromDB.setName(student.getName());
            studentFromDB.setSurname(student.getSurname());

            studentRepository.save(studentFromDB);

            return true;
        }
        return false;
    }

    public boolean removeStudent(String name, String surname) {
        Optional<Student> studentOptional = studentRepository.removeByNameAndSurname(name, surname);
        return studentOptional.isPresent();
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> getAllByNameAndSurname(String name, String surname) {
        return studentRepository.findAllByNameAndSurname(name, surname);
    }
}
