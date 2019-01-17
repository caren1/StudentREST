package com.javagda17.service.studentdemo.controller;

import com.javagda17.service.studentdemo.model.Journal;
import com.javagda17.service.studentdemo.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/journal/")
public class JournalController {

    @Autowired
    private JournalService journalService;

    // TODO: 0. Stwórz metodę na Mappingu '/add' która dodaje dziennik i obiekt dziennika przyjmuje w RequestBody
    @PostMapping("/add")
    public ResponseEntity<Journal> create(@RequestBody Journal journal) {
        Optional<Journal> journalOptional = journalService.create(journal);
        if (journalOptional.isPresent()) {
            return ResponseEntity.ok(journalOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }

    // TODO: 0. Stwórz metodę na Mappingu '/add' która dodaje dziennik i parametry (nazwa klasy, nazwa szkoły, rocznik, nazwisko wychowawcy)
    //  przyjmuje jako OPCJONALNE parametry RequestParam.
    @GetMapping("/add")
    public ResponseEntity<Journal> create(@RequestParam(name = "className", required = false) String className,
                                          @RequestParam(name = "schoolName", required = false) String schoolName,
                                          @RequestParam(name = "year", required = false) Integer year,
                                          @RequestParam(name = "tutor", required = false) String tutor) {
        Optional<Journal> journalOptional = journalService.create(className, schoolName, year, tutor);
        if (journalOptional.isPresent()) {
            return ResponseEntity.ok(journalOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }


    // TODO: 1. Stwórz metodę na Mappingu '/listAll' która zwraca wszystkie dzienniki
    @GetMapping("/get/{id}")
    public ResponseEntity<Journal> get(@PathVariable(name = "id") Long id) {
        Optional<Journal> journalOptional = journalService.getById(id);
        if (journalOptional.isPresent()) {
            return ResponseEntity.ok(journalOptional.get());
        }
        return ResponseEntity.badRequest().build();
    }

    // TODO: 2. Stwórz metodę na Mappingu '/get/{id}' która zwraca dziennik o podanym id
    // TODO: 3. Stwórz metodę na Mappingu '/get' która zwraca dziennik o podanym id (RequestParam)
    // TODO: 4. Stwórz metodę na Mappingu '/delete' która usuwa dziennik o podanym id (RequestParam)
    // TODO: 5. Stwórz metodę na Mappingu '/delete/{identifier}' która usuwa dziennik o podanym id
    // TODO: 6. Stwórz metodę na Mappingu '/update/{identifier}' która aktualizuje dziennik o podanym id
    //  obiekt do zmodyfikowania przekaż jako RequestBody

    // TODO: 7. Stwórz metodę na Mappingu '/update/{identifier}' która aktualizuje dziennik o podanym id
    //  parametry do zmodyfikowania przekaż jako RequestParam. Edytować można: nazwa klasy, nazwa szkoły, rocznik, nazwisko wychowawcy
    //  wszystkie parametry są opcjonalne. Jeśli któryś parametr nei zostanie przekazany nie aktualizuj go !!!! (WAŻNE!)

    // TODO: 8. Stwórz metodę na Mappingu '/studentsOf/{id}' która zwraca studentów z podanego w ścieżce (o id) dziennika.

    // RequestParam(required = false)

//    ####################################################################################################################
    // identyfikatorów - studenta i journal
    @GetMapping("/addStudentToJournal")
    public ResponseEntity<Journal> addStudentToJournal(@RequestParam(name = "studentId") Long id,
                                                       @RequestParam(name = "journalId") Long journalId){
        Optional<Journal> optionalJournal = journalService.addStudentToJournal(id, journalId);
        if (optionalJournal.isPresent()) {
            return ResponseEntity.ok(optionalJournal.get());
        }
        return ResponseEntity.badRequest().build();
    }


}
