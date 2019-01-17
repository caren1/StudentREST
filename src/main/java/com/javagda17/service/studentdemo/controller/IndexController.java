package com.javagda17.service.studentdemo.controller;

import com.javagda17.service.studentdemo.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // zwracamy treść strony
//    @GetMapping("/ping")
//    public String ping() {
//        return "pong";
//    }

    // treść ramki HTTP
    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/studentping")
    public ResponseEntity<Student> pingStudent() {
        return ResponseEntity.ok(new Student(666L, "omen", "nemors"));
    }
}