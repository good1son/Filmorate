package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.RateStorage;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateDbStorage implements RateStorage {
    private final JdbcTemplate jdbcTemplate;
    private final FilmDbStorage filmDbStorage;
    private final EventDbStorage eventDbStorage;

    @Override
    public void rateFilm(int filmId, int userId, int rating) {
        String sql = "INSERT INTO film_ratings (film_id, user_id, rating) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, filmId, userId, rating);
        filmDbStorage.updateFilmRating(filmId);
        eventDbStorage.createEvent(userId, ACTION.RATING, filmId, TARGET.FILM);
    }

    @Override
    public void reRateFilm(int filmId, int userId, int rating) {
        String sql = "UPDATE film_ratings SET rating = ? WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, rating, filmId, userId);
        filmDbStorage.updateFilmRating(filmId);
        eventDbStorage.createEvent(userId, ACTION.EDIT_RATING, filmId, TARGET.FILM);
    }

    @Override
    public void deleteRate(int filmId, int userId) {
        String sql = "DELETE FROM film_ratings WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, filmId, userId);
        filmDbStorage.updateFilmRating(filmId);
        eventDbStorage.createEvent(userId, ACTION.DELETE_RATING, filmId, TARGET.FILM);
    }

    public boolean rateExists(Integer filmId, Integer userId) {
        String sql = "SELECT COUNT(rating) FROM film_ratings WHERE (film_id = ? AND user_id = ?) LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, filmId, userId);
        return count != null && count > 0;
    }
}
