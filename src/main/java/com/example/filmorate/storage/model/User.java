package com.example.filmorate.storage.model;

import com.example.filmorate.annotation.EmptySpace;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Integer id;

    @NotEmpty
    @Email
    private String email;

    @EmptySpace
    private String login;

    @Setter
    private String name;

    @NotNull
    @PastOrPresent
    private  LocalDate birthday;

    private final Set<Long> friendsId = new HashSet<>();

    public User() {};

    public User(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

}
