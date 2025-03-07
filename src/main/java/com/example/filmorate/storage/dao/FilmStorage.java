
package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Film;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {
    public void setDataSource(DataSource dataSource);
    public void add(Film film);

    public Optional<Film> get(int id);
    public Optional<Integer> getFilmIdByName(String name);
    public Collection<Film> getFilms();

    public void putLike(int id);
    public void removeLike(int id);
    public Collection<Integer> getPopular(int count);

    public void update(Film film);
    public void delete(int id);
}
