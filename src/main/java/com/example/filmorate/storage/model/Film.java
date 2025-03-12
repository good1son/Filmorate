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
    private Integer duration;

    @Min(0)
    private Integer mpaId;

    private Float rating;

    public Film() {};

    public Film(String name, String description, LocalDate releaseDate, Set<GENRE> genres,
                Integer duration, Integer mpaId) {
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
