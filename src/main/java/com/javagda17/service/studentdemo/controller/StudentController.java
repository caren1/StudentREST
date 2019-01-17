package com.javagda17.service.studentdemo.controller;

import com.javagda17.service.studentdemo.model.Student;
import com.javagda17.service.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;


    // Jeśli zadeklarujecie RequestParam
    // http://localhost/student?param1=wartoscparam1
    // Muszę użyć nazwy parametru ^^
    // Kolejność nie ma znaczenia


    // Jeśli zadeklarujecie PathVariable
    // http://localhost/student/wartoscparam1
    // [http://localhost/student/] {param1}
    // Nie da się ustawić czegoś opcjonalnie
    // Kolejność ma znaczenie


    // /student/get?id=5
    @GetMapping("/get2")
    public ResponseEntity<Student> getStudentById(@RequestParam(name = "id") Long id) {
        Optional<Student> studentOptional = studentService.getStudent(id);

        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        }

        return ResponseEntity.badRequest().build();
    }


    // todo: zad 1: stwórz metodę getStudentById(Long) która jako parametr (PathVariable) przyjmuje identyfikator studenta
    //  a w wyniku zwraca studenta (JSON) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById2(@PathVariable(name = "id") Long id) {
        Optional<Student> studentOptional = studentService.getStudent(id);

        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        }

        return ResponseEntity.badRequest().build();
    }

    // todo: zad 4: stwórz metodę addStudent(String, String) która jako parametr (RequestParam) przyjmuje imie i nazwisko studenta
    //  a w wyniku zwraca OK jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/add")
    public ResponseEntity addStudent2(@RequestParam String name, @RequestParam String surname) {
        if (studentService.addStudent(name, surname)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 5: stwórz metodę addStudent(Student) która jako parametr (RequestBody - ZROBIMY WSPÓLNIE PO PRZERWIE)
    //  przyjmuje studenta a w wyniku zwraca OK jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student) {
        if (studentService.addStudent(student)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 2: stwórz metodę getStudentById(Long) która jako parametr (PathVariable) przyjmuje imie i nazwisko studenta
    //  a w wyniku zwraca studenta (JSON) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/get/{identifier}")
    public ResponseEntity getById(@PathVariable(name = "identifier") Long identifier) {
        Optional<Student> studentOptional = studentService.getStudent(identifier);

        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        }

        return ResponseEntity.badRequest().build();
    }

    // todo: zad 3: stwórz metodę getStudentById(Long) która jako parametr (RequestParam) przyjmuje imie i nazwisko studenta
    //  a w wyniku zwraca studenta (JSON) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/get")
    public ResponseEntity getById2(@RequestParam(name = "identifier") Long identifier) {
        Optional<Student> studentOptional = studentService.getStudent(identifier);

        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        }

        return ResponseEntity.badRequest().build();
    }

    // ########################################################################## NOWE POLECENIA ##########################################################################

    // todo: zad 7*: stwórz metodę updateStudent(Long, String, String) która jako parametr (PathVariable) przyjmuje identyfikator studenta a jako
    //  RequestParam (dwa parametry) przyjmuje imie i nazwisko które ma zostać zmienione w studencie.
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/update/{identifier}")
    public ResponseEntity update2(@PathVariable(name = "identifier") Long identifier,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname) {
        if (studentService.updateStudent(new Student(identifier, name, surname))) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    // todo: zad 6: stwórz metodę updateStudent(Student student)  która jako parametr (RequestBody) przyjmuje studenta
    //  POST, BODY - > STUDENT
    //  UWAGA! ROZPATRZ PRZYPADEK KIEDY UŻYTKOWNIK NIE PODAŁ IDENTYFIKATORA (SPRÓBUJ NIE DODAĆ GO W JSON, ZOBACZ CO SIĘ WYDARZY)
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Student student) {
        if (student.getId() != null) { // zabezpieczenie anty id-null
            if (studentService.updateStudent(student)) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 8: stwórz metodę deleteStudent(Long)  która jako parametr (PathVariable) przyjmuje id studenta
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @DeleteMapping("/delete/{id}")
    public ResponseEntity remove(@PathVariable(name = "id") Long studentId) {
        if (studentService.removeStudent(studentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 9: stwórz metodę deleteStudent(Long)  która jako parametr (RequestParam) przyjmuje id studenta
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @DeleteMapping("/delete2")
    public ResponseEntity remove2(@RequestParam(name = "id") Long studentId) {
        if (studentService.removeStudent(studentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 10: stwórz metodę deleteStudent(String, String)  która jako parametr (RequestParam) przyjmuje imie i nazwisko studenta
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @DeleteMapping("/delete")
    public ResponseEntity remove(@RequestParam(name = "name") String name, @RequestParam(name = "surname") String surname) {
        if (studentService.removeStudent(name, surname)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 11: stwórz metodę deleteStudent(String, String)  która jako parametr (PathVariable) przyjmuje imie i nazwisko studenta
    //  a w wyniku zwraca (OK) jeśli operacja się powiedzie lub BAD REQUEST jeśli się nie powiedzie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @DeleteMapping("/delete/{name}/{surname}")
    public ResponseEntity remove3(@PathVariable(name = "name") String name, @PathVariable(name = "surname") String surname) {
        if (studentService.removeStudent(name, surname)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // todo: zad 12: stwórz metodę listAll()  która zwraca listę wszystkich studentów w bazie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/listAll")
    public ResponseEntity<List<Student>> listAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    // todo: zad 13: stwórz metodę listAll(String, String)  która przyjmuje imie/nazwisko studenta (RequestParam x2 (OPCJONALNE - required=false))
    //  i zwraca listę studentów z takim imieniem/nazwiskiem w bazie
    // PRZETESTUJ METODĘ UŻYWAJĄC POSTMAN'A
    @GetMapping("/list")
    public ResponseEntity<List<Student>> listAll(@RequestParam(name = "name", required = false) String name,
                                                 @RequestParam(name = "surname", required = false) String surname) {
        List<Student> list = studentService.getAllByNameAndSurname(name, surname);
        return ResponseEntity.ok(list);
    }


}
