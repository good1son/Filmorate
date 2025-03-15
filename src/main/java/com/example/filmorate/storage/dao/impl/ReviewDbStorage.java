package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.ReviewStorage;
import com.example.filmorate.storage.model.Review;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
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
    private final EventDbStorage eventDbStorage;

    @Override
    public Collection<Review> getAllReviews() {
        String sql = "SELECT * FROM reviews";
        return jdbcTemplate.query(sql, new ReviewMapper());
    }

    @Override
    public Collection<Review> getFilmReviews(int id) {
        String sql = "SELECT * FROM reviews WHERE film_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public Collection<Review> getUserReviews(int id) {
        String sql = "SELECT * FROM reviews WHERE user_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public void addReview(int filmId, int userId, String text, Boolean isPositive) {
        String sql = "INSERT INTO reviews (film_id, user_id, review, is_positive) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, filmId, userId, text, isPositive);
        eventDbStorage.createEvent(userId, ACTION.REVIEW, filmId, TARGET.FILM);

    }

    @Override
    public void editReview(int filmId, int userId, String text, Boolean isPositive) {
        String sql = "UPDATE reviews SET review = ?, is_positive = ? WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, text, isPositive, filmId, userId);
        eventDbStorage.createEvent(userId, ACTION.EDIT_REVIEW, filmId, TARGET.FILM);
    }

    @Override
    public void deleteReview(int filmId, int userId) {
        String sql = "DELETE FROM reviews WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, filmId, userId);
        eventDbStorage.createEvent(userId, ACTION.DELETE_REVIEW, filmId, TARGET.FILM);
    }

    public boolean reviewExists(int filmId, int userId) {
        String sql = "SELECT COUNT(id) FROM reviews WHERE (film_id = ? AND user_id = ?) LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, filmId, userId);
        return count != null && count > 0;
    }

    public void updateReviewRating(int reviewId) {
        String sql = "UPDATE reviews SET rating = " +
                "(SELECT SUM(CASE WHEN is_helpful = TRUE THEN 1 ELSE -1 END) " +
                "FROM review_feed " +
                "WHERE review_id = ?) " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, reviewId, reviewId);
    }
}
