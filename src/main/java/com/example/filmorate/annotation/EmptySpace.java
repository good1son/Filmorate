package com.example.filmorate.annotation;

import com.example.filmorate.validator.LoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {LoginValidator.class})
@Documented
public @interface EmptySpace {
    String message() default "не может быть пустым и содержать пробелы";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
