package com.example.filmorate.annotation;

import com.example.filmorate.validator.FilmDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {FilmDateValidator.class})
@Documented
public @interface FilmDate {
    String message() default "должна быть не раньше 28 декабря 1895 и не в будущем";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
