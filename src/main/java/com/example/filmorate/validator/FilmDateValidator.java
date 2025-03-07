package com.example.filmorate.validator;

import com.example.filmorate.annotation.FilmDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FilmDateValidator implements ConstraintValidator<FilmDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
            return date != null && !date.isBefore(LocalDate.of(1895,12,28))
                    && !date.isAfter(LocalDate.now());
    }
}
