package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.FilmStorage;
import com.example.filmorate.storage.model.Film;
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
    public Optional<Film> get(int id) {
        return Optional.empty();
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
    public void putLike(int id) {

    }

    @Override
    public void removeLike(int id) {

    }


    @Override
    public Collection<Integer> getPopular(int count) {
        return List.of();
    }


    @Override
    public void update(Film film) {

    }

    @Override
    public void delete(int id) {

    }
}
