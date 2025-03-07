package com.example.filmorate.controller;

import com.example.filmorate.exception.*;
import com.example.filmorate.exception.ErrorMessage;
import com.example.filmorate.exception.ErrorResponse;
import com.example.filmorate.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ValidationErrorResponse validationExceptions(MethodArgumentNotValidException e) {
        final List<ErrorResponse> errors = e.getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ValidationErrorResponse(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFound(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorMessage filmAlreadyExists(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }


    /*@ExceptionHandler(FilmNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage filmNotFound(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFound(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(GenreNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage genreNotFound(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }*/
}
