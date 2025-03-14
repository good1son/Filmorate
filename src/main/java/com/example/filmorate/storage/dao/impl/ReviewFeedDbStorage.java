package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.storage.dao.ReviewFeedStorage;
import com.example.filmorate.storage.model.ReviewFeed;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import com.example.filmorate.storage.util.ReviewFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewFeedDbStorage implements ReviewFeedStorage {
    private final JdbcTemplate jdbcTemplate;
    private final ReviewDbStorage reviewDbStorage;
    private final EventDbStorage eventDbStorage;

    @Override
    public Collection<ReviewFeed> getAllReviewFeed() {
        String sql = "SELECT * FROM review_feed";
        return jdbcTemplate.query(sql, new ReviewFeedMapper());
    }

    @Override
    public Collection<ReviewFeed> getUserReviewFeed(int id) {
        String sql = "SELECT * FROM review_feed WHERE user_id =?";
        return jdbcTemplate.query(sql, new ReviewFeedMapper(), id);
    }

    @Override
    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        String sql = "INSERT INTO review_feed (review_id, user_id, is_helpful) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reviewId, userId, isHelpful);
        reviewDbStorage.updateReviewRating(reviewId);
        eventDbStorage.createEvent(userId, ACTION.REVIEW_FEED, reviewId, TARGET.REVIEW);
    }

    @Override
    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        String sql = "UPDATE review_feed SET is_helpful = ? WHERE review_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, isHelpful, reviewId, userId);
        reviewDbStorage.updateReviewRating(reviewId);
        eventDbStorage.createEvent(userId, ACTION.EDIT_REVIEW_FEED, reviewId, TARGET.REVIEW);
    }

    @Override
    public void deleteReviewFeed(int reviewId, int userId) {
        String sql = "DELETE FROM review_feed WHERE review_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, reviewId, userId);
        reviewDbStorage.updateReviewRating(reviewId);
        eventDbStorage.createEvent(userId, ACTION.DELETE_REVIEW_FEED, reviewId, TARGET.REVIEW);
    }

    public boolean reviewFeedExists(int reviewId, int userId) {
        String sql = "SELECT COUNT(id) FROM review_feed WHERE (review_id = ? AND user_id = ?) LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, reviewId, userId);
        return count != null && count > 0;
    }
}
