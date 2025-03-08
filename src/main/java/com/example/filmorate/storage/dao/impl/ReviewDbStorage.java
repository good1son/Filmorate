package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.storage.dao.ReviewStorage;
import com.example.filmorate.storage.model.ReviewFeed;
import com.example.filmorate.storage.util.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewDbStorage implements ReviewStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<ReviewFeed> getFilmReviews(int id) {
        String sql = "SELECT user_id AS id, review FROM reviews WHERE film_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public Collection<ReviewFeed> getUserReviews(int id) {
        String sql = "SELECT film_id AS id, review FROM reviews WHERE user_id = ?";
        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }

    @Override
    public void addReview(int filmId, int userId, String text, Boolean isPositive) {
        try {
            String sql = "INSERT INTO reviews (film_id, user_id, review, is_positive) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, filmId, userId, text, isPositive);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("Вы уже оставляли отзыв на данный фильм");
        }

    }

    @Override
    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        try {
            String sql = "INSERT INTO review_feed (review_id, user_id, is_helpful) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, reviewId, userId, isHelpful);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("Вы уже оставляли оценку на данный отзыв");
        }
        updateReviewRating(reviewId);
    }

    @Override
    public void editReview(int filmId, int userId, String text, Boolean isPositive) {
        String sql = "UPDATE reviews SET review = ?, is_positive = ? WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, text, isPositive, filmId, userId);
    }

    @Override
    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        String sql = "UPDATE review_feed SET is_helpful = ? WHERE review_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, isHelpful, reviewId, userId);
        updateReviewRating(reviewId);
    }

    @Override
    public void deleteReview(int filmId, int userId) {
        String sql = "DELETE FROM reviews WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, filmId, userId);
    }

    @Override
    public void deleteReviewFeed(int reviewId, int userId) {
        String sql = "DELETE FROM review_feed WHERE review_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, reviewId, userId);
        updateReviewRating(reviewId);
    }

    private void updateReviewRating(int reviewId) {
        String sql = "UPDATE reviews SET rating = " +
                "(SELECT SUM(CASE WHEN is_helpful = TRUE THEN 1 ELSE -1 END) " +
                "FROM review_feed " +
                "WHERE review_id = ?) " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, reviewId, reviewId);
    }
}
