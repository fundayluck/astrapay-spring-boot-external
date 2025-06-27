package com.astrapay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.astrapay.utility.ResponseHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<Object> handleNoteNotFound(NoteNotFoundException ex) {
        return ResponseHandler.generateError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleUUIDError(IllegalArgumentException ex) {
        return ResponseHandler.generateError("Invalid UUID format",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUnhandled(Exception ex) {
        return ResponseHandler.generateError("Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
