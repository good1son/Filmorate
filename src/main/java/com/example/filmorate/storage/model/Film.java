package com.example.filmorate.storage.model;

import com.example.filmorate.annotation.FilmDate;
import com.example.filmorate.storage.model.type.GENRE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class Film {
    private Integer id;

    @NotNull @NotBlank
    private String name;

    @NotNull @Size(min = 1, max = 200)
    private String description;

    @FilmDate
    private LocalDate releaseDate;

    private Set<GENRE> genres;

    @Min(1)
    private int duration;

    @Min(0)
    private int mpaId;

    @JsonIgnore
    private final Set<Integer> likesSet = new HashSet<>();

    private int likes;

    public Film() {};

    public Film(String name, String description, LocalDate releaseDate, Set<GENRE> genres,
                int duration, int mpaId) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.duration = duration;
        this.mpaId = mpaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(name.toLowerCase(), film.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
