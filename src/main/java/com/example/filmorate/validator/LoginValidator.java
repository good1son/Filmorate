package com.example.filmorate.validator;

import com.example.filmorate.annotation.EmptySpace;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LoginValidator implements ConstraintValidator<EmptySpace, String> {
    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return login != null && !login.isBlank() && !login.contains(" ");
    }
}