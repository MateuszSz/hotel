package com.szymczak.controller;

import com.szymczak.exception.DatesException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;


@ControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(DatesException.class)
    public String handleCustomDataException(DatesException ex) {
        log.error("DATE EXCEPTION - " + ex.getErrorMessage());
        return "error";
    }

    @ExceptionHandler(ParseException.class)
    public String handleParseException(ParseException ex) {
        log.error("PARSE EXCEPTION - " + ex.getMessage());
        return "error";

    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex) {
        log.error("NUMBER FORMAT EXCEPTION - " + ex.getMessage());
        return "error";

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("CONSTRAINT VIOLATION EXCEPTION - " + ex.getMessage());
        return "registerError";

    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex) {
        log.error("NULL POINTER EXCEPTION - " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        log.error("INDEX OUT OF BOUNDS - " + ex.getMessage());
        return "error";
    }
}
