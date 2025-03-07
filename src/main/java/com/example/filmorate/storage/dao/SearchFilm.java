package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;

import java.time.LocalDate;
import java.util.Collection;

public interface SearchFilm {
    public Collection<Integer> searchFilms(String name, GENRE genre, String year, MPA mpa);
}
