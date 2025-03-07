package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.ReviewStorage;
import com.example.filmorate.storage.model.Review;
import com.example.filmorate.storage.util.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewDbStorage implements ReviewStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Review> getFilmReviews(int id) {
        String sql = "SELECT user_id AS id, review FROM reviews WHERE film_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public Collection<Review> getUserReviews(int id) {
        String sql = "SELECT film_id AS id, review FROM reviews WHERE user_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public void addReview(int filmId, int userId, String text) {
        String sql = "INSERT INTO reviews (film_id, user_id, review) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, filmId, userId, text);
    }

    @Override
    public void editReview(int filmId, int userId, String text) {
        String sql = "UPDATE reviews SET review = ? WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, text, filmId, userId);
    }

    @Override
    public void deleteReview(int filmId, int userId) {
        String sql = "DELETE FROM reviews WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, filmId, userId);
    }
}
