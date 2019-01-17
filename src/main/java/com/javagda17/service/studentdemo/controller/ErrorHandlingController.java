package com.javagda17.service.studentdemo.controller;

import com.javagda17.service.studentdemo.exceptions.JournalNotFound;
import com.javagda17.service.studentdemo.exceptions.StudentNotFound;
import com.javagda17.service.studentdemo.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({StudentNotFound.class})
    public ResponseEntity<ErrorMessage> handleStudentNotFound() {
        ErrorMessage message = new ErrorMessage();
        message.setErrorMessage("StudentNotFound");
        message.setReason("Given Id does not exist.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    // TODO: Stwórz exception handler dla przypadku kiedy nie można znaleźć identyfikatora Journala
    //  (podobny błąd w metodzie w JournalService)

    @ExceptionHandler({JournalNotFound.class})
    public ResponseEntity<ErrorMessage> handleDziennikNotFound() {
        ErrorMessage message = new ErrorMessage();
        message.setErrorMessage("JournalNotFound");
        message.setReason("Given Journal ID does not exist.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
