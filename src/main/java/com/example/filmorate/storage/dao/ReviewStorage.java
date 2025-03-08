package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.ReviewFeed;

import java.util.Collection;

public interface ReviewStorage {
    public Collection<ReviewFeed> getFilmReviews(int id);
    public Collection<ReviewFeed> getUserReviews(int id);
    public void addReview(int filmId, int userId, String text, Boolean isPositive);
    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful);
    public void editReview(int filmId, int userId, String text, Boolean isPositive);
    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful);
    public void deleteReview(int filmId, int userId);
    public void deleteReviewFeed(int reviewId, int userId);
}
