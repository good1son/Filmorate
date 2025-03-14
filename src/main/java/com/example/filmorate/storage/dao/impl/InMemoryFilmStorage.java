package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.FilmStorage;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final AtomicInteger id = new AtomicInteger(1);
    private final Map<Integer, Film> films = new HashMap<>();
    //private final Set<Film> topFilms = new TreeSet<>(new LikeComparator());

    @Override
    public void setDataSource(DataSource dataSource) {

    }

    @Override
    public void add(Film film) {

    }

    @Override
    public Film get(int id) {
        return new Film();
    }

    @Override
    public Optional<Integer> getFilmIdByName(String name) {
        return Optional.empty();
    }

    @Override
    public Collection<Film> getFilms() {
        return List.of();
    }

    @Override
    public Collection<Film> searchFilms(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                        List<String> directors, List<Integer> genresId, List<Integer> mpaId,
                                        Integer count, String order, String sort) {
        return List.of();
    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void delete(int id) {

    }
}
