package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Genre;
import com.example.filmorate.storage.model.type.GENRE;

import java.util.Collection;
import java.util.Optional;

public interface GenreStorage {
    public Optional<GENRE> getGenre(int id);
    public Collection<GENRE> getGenres();
    public Collection<GENRE> getFilmGenres(int id);
    public void addGenre(int id, Collection<Integer> genreId);
}
