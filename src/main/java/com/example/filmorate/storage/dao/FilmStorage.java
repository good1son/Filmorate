
package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmStorage {
    public void setDataSource(DataSource dataSource);
    public void add(Film film);

    public Optional<Film> get(int id);
    public Optional<Integer> getFilmIdByName(String name);
    public Collection<Film> getFilms();

    public void rateFilm(int filmId, int userId, int rating);
    public void reRateFilm(int filmId, int userId, int rating);
    public void deleteRate(int filmId, int userId);

    public Collection<Film> searchFilms(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                       List<Integer> genresId, List<Integer> mpaId, Integer count,
                                        String order, String sort);

    public void update(Film film);
    public void delete(int id);
}
